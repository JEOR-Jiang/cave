package cave.utils;

/**
 * Created by Jeor on 2016/5/4.
 */
public final class TransformData {
    public static final String fusion(Number[] numbers,String chart){
        StringBuilder sb=new StringBuilder();
        boolean flag=true;
        for(Number number:numbers){
            if(flag){
                flag=false;
                sb.append(number);
            }else {
                sb.append(chart);
                sb.append(number);
            }
        }
        return sb.toString();
    }
}
