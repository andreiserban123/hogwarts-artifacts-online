package ro.ase.ro.hogwartsartifactsonline.wizard;

import jakarta.persistence.*;
import ro.ase.ro.hogwartsartifactsonline.artifact.Artifact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wizard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "owner")
    private List<Artifact> artifacts;

    public Wizard() {
        this.artifacts = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }


    public void addArtifact(Artifact a) {
        a.setOwner(this);
        artifacts.add(a);
    }

    public Integer getNumberOfArtifacts() {
        return artifacts.size();
    }
}
