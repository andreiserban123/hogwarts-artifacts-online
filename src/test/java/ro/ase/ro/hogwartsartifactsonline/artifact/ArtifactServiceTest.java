package ro.ase.ro.hogwartsartifactsonline.artifact;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.ase.ro.hogwartsartifactsonline.exceptions.ArtifactNotFoundException;
import ro.ase.ro.hogwartsartifactsonline.wizard.Wizard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {


    List<Artifact> artifacts = new ArrayList<>();

    @Mock
    ArtifactRepository artifactRepository;

    @InjectMocks
    ArtifactService artifactService;

    @BeforeEach
    void setUp() {
        Artifact a1 = new Artifact();
        a1.setId("1250808601744904191");
        a1.setName("Deluminator");
        a1.setDescription("A Deluminator is a device invented by Albus Dumbledore that resembles a cigarette lighter. It is used to remove or absorb (as well as return) the light from any light source to provide cover to the user.");
        a1.setImageUrl("https://hogwartsartifactsonline.blob.core.windows.net/artifact-image-container/deluminator.jpg");

        Artifact a2 = new Artifact();
        a2.setId("1250808601744904192");
        a2.setName("Invisibility Cloak");
        a2.setDescription("An invisibility cloak is used to make the wearer invisible.");
        a2.setImageUrl("https://hogwartsartifactsonline.blob.core.windows.net/artifact-image-container/invisibility-cloak.jpg");


        artifacts.add(a1);
        artifacts.add(a2);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {
//        Given.Arrange inputs and targets. Define the behavior of the Mock
        Artifact a = new Artifact();
        a.setId("123442132");
        a.setName("Invisibility Cloak");
        a.setDescription("An invisibility cloak is used to make the wearer invisible");
        a.setImageUrl("ImageUrl");

        Wizard w = new Wizard();
        w.setId(2);
        w.setName("Harry Potter");

        a.setOwner(w);

        given(artifactRepository.findById("123442132")).willReturn(Optional.of(a));

//        When. Call the method you want to test

        Artifact returnedArtifact = artifactService.findById("123442132");
//        Then. Assert the resutl

        assertThat(returnedArtifact.getId()).isEqualTo(a.getId());
        assertThat(returnedArtifact.getName()).isEqualTo(a.getName());
        assertThat(returnedArtifact.getDescription()).isEqualTo(a.getDescription());
        assertThat(returnedArtifact.getImageUrl()).isEqualTo(a.getImageUrl());

        verify(artifactRepository, times(1)).findById("123442132");
    }

    @Test
    void testFindByIdNotFound() {
//        Given
        given(artifactRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

//        when
        Throwable thrown = catchThrowable(() -> artifactService.findById("123442132"));

//        then

        assertThat(thrown).isInstanceOf(ArtifactNotFoundException.class)
                .hasMessage("Could not find artifact with Id 123442132 :(");
    }


    @Test
    void testFindAllSuccess() {
//        Given
        given(artifactRepository.findAll()).willReturn(artifacts);

//        WHEN

        var lista = artifactService.findAll();

//        THen

        assertThat(lista).isNotNull();
        assertThat(lista).isNotEmpty();
        assertThat(lista.size()).isEqualTo(artifacts.size());
        assertThat(lista.get(0).getId()).isEqualTo(artifacts.get(0).getId());
        assertThat(lista.get(0).getName()).isEqualTo(artifacts.get(0).getName());
        verify(artifactRepository, times(1)).findAll();
    }
}