package com.blog.blogbakend.modals;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;



@Entity
@Table(name = "blogs")
public class blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blogid")
    private int blogid;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private Users user;



    public int getId() {
        return blogid;
    }

    public void setId(int id) {
        this.blogid = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

//    public Collection<Users> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(Collection<Users> followers) {
//        this.followers = followers;
//    }
//
//    public Collection<Users> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(Collection<Users> following) {
//        this.following = following;
//    }
}
