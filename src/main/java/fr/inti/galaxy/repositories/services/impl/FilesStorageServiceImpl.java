package fr.inti.galaxy.repositories.services.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.inti.galaxy.entities.Document;
import fr.inti.galaxy.repositories.services.FilesStorageService;

@Service
@Transactional
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("uploads");
  
  @Autowired
  private DocumentServiceImpl documentService;

  @Autowired
  private UtilisateurServiceImpl utilisateurService;
  
  @Override
  public void init() {
    try {
      Files.createDirectories(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file, String username) {
    try {
      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
      Document docToSave = new Document();
      docToSave.setDocumentUrl("http://localhost:8085/files/"+file.getOriginalFilename());
      docToSave.setDocumentName(file.getOriginalFilename());
      documentService.save(docToSave);
      utilisateurService.findUtilisateurByUtilisateurname(username).getDocuments().add(docToSave);
      utilisateurService.save( utilisateurService.findUtilisateurByUtilisateurname(username));
    } catch (Exception e) {
      if (e instanceof FileAlreadyExistsException) {
        throw new RuntimeException("A file of that name already exists.");
      }

      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public boolean delete(String filename) {
    try {
      Path file = root.resolve(filename);
      return Files.deleteIfExists(file);
    } catch (IOException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

}
