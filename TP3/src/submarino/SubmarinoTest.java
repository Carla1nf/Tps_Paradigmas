package submarino;
public class SubmarinoTest {

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

    public class NumeroTest {
        private Numero zero;
        private Numero one;
        private Numero two;
        private Numero three;
        private Numero four;
        private Numero five;
        private Numero eight;
        private Numero negativeOne;
        private Numero negativeTwo;
        private Numero oneHalf;
        private Numero oneFifth;
        private Numero twoFifth;
        private Numero twoTwentyfifth;
        private Numero fiveHalfs;
        private Numero sixFifths;
        private Numero negativeOneHalf;

        @BeforeEach public void setUp() {
            zero = Numero.with( 0 );
            one = Numero.with( 1 );
            two = Numero.with( 2 );
            three = Numero.with( 3 );
            four = Numero.with( 4 );
            five = Numero.with( 5 );
            eight = Numero.with( 8 );
            oneFifth = Numero.with( 1, 5 );
            oneHalf = Numero.with( 1, 2 );
            twoFifth = Numero.with( 2, 5 );
            twoTwentyfifth = Numero.with( 2, 25 );
            fiveHalfs = Numero.with( 5, 2 );
            sixFifths = Numero.with( 6, 5 );
            negativeOne = Numero.with( -1 );
            negativeTwo = Numero.with( -2 );
            negativeOneHalf = Numero.with( -1, 2 );
        }

        @Test



}
