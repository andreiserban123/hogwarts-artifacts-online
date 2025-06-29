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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {

    @Mock
    ArtifactRepository artifactRepository;

    @InjectMocks
    ArtifactService artifactService;

    @BeforeEach
    void setUp() {
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
}