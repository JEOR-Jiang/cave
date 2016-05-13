package cave.utils;

/**
 * Created by Jeor on 2016/5/4.
 * 转换工具
 */
public final class TransformData {
    /**
     * 数组融合char为分隔符
     * @param numbers   [1,2,3]
     * @param chart "abc"
     * @return 1abc2abc3
     */
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
