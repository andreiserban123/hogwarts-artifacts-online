package ro.ase.ro.hogwartsartifactsonline.artifact.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.ase.ro.hogwartsartifactsonline.artifact.Artifact;
import ro.ase.ro.hogwartsartifactsonline.artifact.dto.ArtifactDto;
import ro.ase.ro.hogwartsartifactsonline.wizard.converter.WizardToWizardDtoConverter;

@Component
public class ArtifactToArtifactDtoConverter implements Converter<Artifact, ArtifactDto> {

    private final WizardToWizardDtoConverter wizardToWizardDtoConverter;

    public ArtifactToArtifactDtoConverter(WizardToWizardDtoConverter wizardToWizardDtoConverter) {
        this.wizardToWizardDtoConverter = wizardToWizardDtoConverter;
    }

    @Override
    public ArtifactDto convert(Artifact source) {
        return new ArtifactDto(source.getId(),
                source.getName(),
                source.getDescription(),
                source.getImageUrl(),
                source.getOwner() != null ? wizardToWizardDtoConverter.convert(source.getOwner()) : null
        );
    }
}
