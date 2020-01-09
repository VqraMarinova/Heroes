package app.domain.entities;

import javax.persistence.*;

@Entity
@Table
public class Hero extends BaseEntity {

    private String name;
    private Clazz clazz;
    private int level;



    public Hero() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "clazz", nullable = false)
    @Enumerated(EnumType.STRING)
    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
