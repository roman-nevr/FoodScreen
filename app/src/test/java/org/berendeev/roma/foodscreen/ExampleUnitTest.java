package org.berendeev.roma.foodscreen;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        List<B<A>> list = new ArrayList<>();
        B<C> ba = new B<>();
        ba.f(new C());
    }

    class A{

    }

    class C extends A{

    }
    class D{

    }

    class B<E extends A>{
        void f(E arg){

        }
    }
}