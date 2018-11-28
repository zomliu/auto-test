package FastJson;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Word
{
    private String a;
    private int b;
    private boolean c;
    private Date date;
    private List<User> list;
    private Map<String, Object> map;

    public String getA()
    {
        return this.a;
    }

    public void setA(String a)
    {
        this.a = a;
    }

    public int getB()
    {
        return this.b;
    }

    public void setB(int b)
    {
        this.b = b;
    }

    public boolean getC()
    {
        return this.c;
    }

    public void setC(boolean c)
    {
        this.c = c;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public List getList()
    {
        return this.list;
    }

    public void setList(List<User> list)
    {
        this.list = list;
    }

    public Map getMap()
    {
        return this.map;
    }

    public void setMap(Map<String, Object> map)
    {
        this.map = map;
    }
}
