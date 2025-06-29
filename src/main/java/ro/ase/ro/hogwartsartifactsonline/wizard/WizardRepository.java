package ro.ase.ro.hogwartsartifactsonline.wizard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface WizardRepository extends JpaRepository<Wizard, Long>, Serializable {
}
