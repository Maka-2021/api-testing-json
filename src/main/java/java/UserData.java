package java;

public class UserData
{
    private int id;
    private int userId;
    private String title;
    private String body;

    public UserData(){

    }

    public UserData(int userId, int id,  String title, String body){
        setUserId(userId);
        setId(id);
        setTitle(title);
        setBody(body);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    @Override
    public String toString()
    {
        return "[" +
                '{' +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}' +
                "]";
    }

}
