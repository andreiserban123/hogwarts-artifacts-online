package ro.ase.ro.hogwartsartifactsonline.artifact;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.ase.ro.hogwartsartifactsonline.artifact.converter.ArtifactToArtifactDtoConverter;
import ro.ase.ro.hogwartsartifactsonline.system.Result;
import ro.ase.ro.hogwartsartifactsonline.system.StatusCode;

@RestController
public class ArtifactController {
    private final ArtifactService artifactService;
    private final ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter;

    public ArtifactController(ArtifactService artifactService, ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter) {
        this.artifactService = artifactService;
        this.artifactToArtifactDtoConverter = artifactToArtifactDtoConverter;
    }


    @GetMapping("/api/v1/artifacts/{artifactId}")
    public Result findArtifactById(@PathVariable String artifactId) {
        var artifact = artifactService.findById(artifactId);

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Find One Success",
                artifactToArtifactDtoConverter.convert(artifact)
        );
    }
}
