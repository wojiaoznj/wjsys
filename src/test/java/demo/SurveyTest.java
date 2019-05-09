package demo;

import com.diagens.bean.SurveyBean;
import com.diagens.util.API;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author ZNJ
 * @create 2018-12-27 11:28
 */
public class SurveyTest {

    @Test
    public void test1(){
        List<Map<Object,Object>> list=new ArrayList<>();
        Map<Object,Object> map=new HashMap<>();
        map.put(1,1);
        map.put(2,2);
        list.add(map);
        Map<Object,Object> map1=new HashMap<>();
        map1.put(1,1);
    }
}
