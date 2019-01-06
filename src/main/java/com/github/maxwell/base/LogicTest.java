package com.github.maxwell.base;

import org.junit.Test;

/*
思维逻辑测试
@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/
public class LogicTest {

    @Test
    public void test1() {
        int i = 0;
        i = ++i;
        System.out.println(i);
    }

    @Test
    public  void sumChild(){
        int x; //公鸡
        int y; //母鸡
        int z; // 小鸡;
        for(x = 1; x<33; x++){
            for(y = 1; y<19; y ++ ){
                z = 100 - x - y;
                if( z%3 == 0 && ((3*x + 5*y + z/3) == 100)){
                    System.out.println("x:" + x + ",y:" + y +", z:" + z);
                }
            }
        }
    }
}
