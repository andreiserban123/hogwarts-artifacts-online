package ro.ase.ro.hogwartsartifactsonline.artifact;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.ase.ro.hogwartsartifactsonline.exceptions.ArtifactNotFoundException;

@Service
@Transactional
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    public ArtifactService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    public Artifact findById(String artifactId) {
        return artifactRepository.findById(artifactId).orElseThrow(() -> new ArtifactNotFoundException(artifactId));
    }
}
