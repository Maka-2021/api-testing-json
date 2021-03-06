package training.com;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PostData
{
    private int id;
    private String Email;
    private String FName;
    private String PostId;

    //public PostData(){}

    @JsonCreator
    public PostData(int id, String Email, String FName, String PostId){

        setId(id);
        setEmail(Email);
        setFName(FName);
        setPostId(PostId);

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFName()
    {
        return FName;
    }

    public void setFName(String FName)
    {
        this.FName = FName;
    }

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String Email)
    {
        this.Email = Email;
    }

    public String getPostId()
    {
        return PostId;
    }

    public void setPostId(String PostId)
    {
        this.PostId = PostId;
    }

    @Override
    public String toString()
    {
        return "PostData{" +
                "id=" + id +
                ", Email='" + Email + '\'' +
                ", FName='" + FName + '\'' +
                ", PostId='" + PostId + '\'' +
                '}';
    }
}
