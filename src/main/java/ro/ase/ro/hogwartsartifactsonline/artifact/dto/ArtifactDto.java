package ro.ase.ro.hogwartsartifactsonline.artifact.dto;

import ro.ase.ro.hogwartsartifactsonline.wizard.dto.WizardDto;

public record ArtifactDto(String id,
                          String name,
                          String description,
                          String imageUrl,
                          WizardDto owner) {
}
