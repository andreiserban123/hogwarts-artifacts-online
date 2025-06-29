package ro.ase.ro.hogwartsartifactsonline.wizard.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.ase.ro.hogwartsartifactsonline.wizard.Wizard;
import ro.ase.ro.hogwartsartifactsonline.wizard.dto.WizardDto;

@Component
public class WizardToWizardDtoConverter implements Converter<Wizard, WizardDto> {
    @Override
    public WizardDto convert(Wizard source) {
        return new WizardDto(source.getId(),
                source.getName(),
                source.getNumberOfArtifacts());
    }
}
