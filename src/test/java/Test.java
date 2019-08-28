import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String args[]) throws Exception {
        String aa = "2";
        Float taskHourShort = Float.parseFloat(aa);
        taskHourShort = 0f;
        System.out.println(taskHourShort);

        Set<String> a = new HashSet<>(2);
        a.add("a");
//        a.add("b");
//        a.add("c");
//        a.add("d");
        System.out.println(a.size());
        Test test = new Test();
        test.getMonth("2018-01", "2018-01");
    }

    public List<String> getMonth(String startYearMouth, String endYearMouth) throws Exception {
        Date d1 = new SimpleDateFormat("yyyy-MM").parse(startYearMouth);//定义起始日期
        Date d2 = new SimpleDateFormat("yyyy-MM").parse(endYearMouth);//定义结束日期
        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(d1);//设置日期起始时间
        List<String> list = new ArrayList<>();
        while (dd.getTime().before(d2)) {//判断是否到结束日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String str = sdf.format(dd.getTime());
            System.out.println(str);//输出日期结果
            list.add(str);
            dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
        }
        return list;
    }

}
