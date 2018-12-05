package pl.akademiakodu.kwejk2.model;


import javax.persistence.*;

@Entity
public class MemComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;

    @ManyToOne
    private Mem mem;

    public Mem getMem() {
        return mem;
    }

    public void setMem(Mem mem) {
        this.mem = mem;
    }



    //Constructors
    public MemComment(){}

    public MemComment(String comment) {
        this.comment = comment;
    }

    //GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return getComment();
    }


}
