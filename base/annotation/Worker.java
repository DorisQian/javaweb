package annotation;


@MyAnno(age = 2, show1="abc", per=Person.P1, anno2 = @MyAnno2, strs={"aaa", "bbb"})
@MyAnno3
public class Worker {

    @MyAnno2()
    public void show(){

    }
    @MyAnno3
    public String name = "test";

}
