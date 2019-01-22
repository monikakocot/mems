package pl.akademiakodu.kwejk2.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String url;

    @OneToMany(mappedBy = "mem",cascade = CascadeType.ALL) //creates column 'mem_id' in table 'mem_comments'
    //@Column(name = "mem_id") // j.w. works in the same way also without it

    List<MemComment> comments = new ArrayList<>();

    public void addComment(MemComment memComment){
        comments.add(memComment);
        memComment.setMem(this);
    }

    public List<MemComment> getComments() {
        return comments;
    }

    public void setComments(List<MemComment> comments) {
        this.comments = comments;
    }

    @OneToOne(optional = true) // creates column 'category_id' in table 'mem'
    //@JoinColumn(name="category_id")// j.w. but works also without it :)
    //@Column(name = "category_id") // not allowed on a @oneToOne property
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    //GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
