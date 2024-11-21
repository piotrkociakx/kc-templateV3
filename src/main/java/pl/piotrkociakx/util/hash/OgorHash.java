package pl.piotrkociakx.util.hash;

import javax.crypto.Cipher; import javax.crypto.SecretKey; import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets; import java.util.Base64; import java.util.HashMap; import java.util.Map;
import org.apache.commons.codec.binary.Base32;

public class OgorHash {
    private static final Map<Character, String> A = a();
    private static final String B = "AES"; private static final String C = "1234567890123456";
    private static final SecretKey D = new SecretKeySpec(C.getBytes(StandardCharsets.UTF_8), B);

    private static Map<Character, String> a() {
        Map<Character, String> e = new HashMap<>();
        e.put('A', ".-"); e.put('B', "-..."); e.put('C', "-.-."); e.put('D', "-.."); e.put('E', ".");
        e.put('F', "..-."); e.put('G', "--."); e.put('H', "...."); e.put('I', ".."); e.put('J', ".---");
        e.put('K', "-.-"); e.put('L', ".-.."); e.put('M', "--"); e.put('N', "-."); e.put('O', "---");
        e.put('P', ".--."); e.put('Q', "--.-"); e.put('R', ".-."); e.put('S', "..."); e.put('T', "-");
        e.put('U', "..-"); e.put('V', "...-"); e.put('W', ".--"); e.put('X', "-..-"); e.put('Y', "-.--");
        e.put('Z', "--.."); e.put('1', ".----"); e.put('2', "..---"); e.put('3', "...--");
        e.put('4', "....-"); e.put('5', "....."); e.put('6', "-...."); e.put('7', "--...");
        e.put('8', "---.."); e.put('9', "----."); e.put('0', "-----"); e.put(' ', "/");
        e.put('!', "-.-.--"); e.put('.', ".-.-.-"); e.put(',', "--..--"); e.put(':', "---...");
        e.put(';', "-.-.-."); e.put('?', "..--.."); e.put('\'', ".----."); e.put('-', "-....-");
        e.put('/', "-..-."); e.put('@', ".--.-."); e.put('"', ".-..-.");
        // Dodane polskie znaki
        e.put('Ą', ".-"); e.put('Ć', "-.-."); e.put('Ę', "."); e.put('Ł', ".-..");
        e.put('Ń', "-."); e.put('Ó', "---"); e.put('Ś', "..."); e.put('Ź', "--..");
        e.put('Ż', "--..");
        return e;
    }

    private static String h(String i) {
        StringBuilder j = new StringBuilder();
        for (char k : i.toUpperCase().toCharArray()) j.append(A.getOrDefault(k, "")).append(" ");
        return j.toString().trim();
    }

    private static String l(String m) {
        StringBuilder n = new StringBuilder();
        String[] o = m.split(" ");
        for (String p : o) {
            n.append(A.entrySet().stream().filter(q -> q.getValue().equals(p)).map(Map.Entry::getKey).findFirst().orElse(' '));
        }
        return n.toString();
    }

    private static Map<Character, Character> r() {
        Map<Character, Character> s = new HashMap<>();
        char[] t = "ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŹŻ".toCharArray();
        char[] u = "ZXYWVUTSRQPONMLKJIHGFEDCBAŻŹŚÓŃŁĘĆĄ".toCharArray();
        for (int v = 0; v < t.length; v++) s.put(t[v], u[v]);
        return s;
    }

    private static Map<Character, Character> w() {
        Map<Character, Character> x = new HashMap<>();
        char[] y = "ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŹŻ".toCharArray();
        char[] z = "ZXYWVUTSRQPONMLKJIHGFEDCBAŻŹŚÓŃŁĘĆĄ".toCharArray();
        for (int aa = 0; aa < y.length; aa++) x.put(z[aa], y[aa]);
        return x;
    }

    private static String ab(String ac) {
        StringBuilder ad = new StringBuilder();
        Map<Character, Character> ae = r();
        for (char af : ac.toUpperCase().toCharArray()) ad.append(ae.getOrDefault(af, af));
        return ad.toString();
    }

    private static String ag(String ah) {
        StringBuilder ai = new StringBuilder();
        Map<Character, Character> aj = w();
        for (char ak : ah.toUpperCase().toCharArray()) ai.append(aj.getOrDefault(ak, ak));
        return ai.toString();
    }

    private static String al(String am) {
        StringBuilder an = new StringBuilder();
        for (char ao : am.toUpperCase().toCharArray()) {
            if (Character.isLetter(ao)) an.append((ao - 'A' + 1)).append(" ");
            else if (ao == ' ') an.append("/ ");
            else an.append(ao).append(" ");
        }
        return an.toString().trim();
    }

    private static String ap(String aq) {
        StringBuilder ar = new StringBuilder();
        String[] as = aq.split(" ");
        for (String at : as) {
            if (at.equals("/")) ar.append(" ");
            else {
                try { ar.append((char) ('A' + Integer.parseInt(at) - 1));
                } catch (NumberFormatException au) { ar.append(at); }
            }
        }
        return ar.toString();
    }

    public static String c(String aw, Integer ax) {
        if (ax <= 1) {
            return new Base32().encodeToString(e(h(al(ab(aw)))).getBytes());
        } else {
            byte[] ay = new Base32().decode(aw);
            return ag(ap(l(d(new String(ay, StandardCharsets.UTF_8)))));
        }
    }

    private static String e(String az) {
        try {
            Cipher ba = Cipher.getInstance(B);
            ba.init(Cipher.ENCRYPT_MODE, D);
            byte[] bb = ba.doFinal(az.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bb);
        } catch (Exception bc) {
            throw new RuntimeException("Error during encryption.", bc);
        }
    }

    private static String d(String bd) {
        try {
            Cipher be = Cipher.getInstance(B);
            be.init(Cipher.DECRYPT_MODE, D);
            byte[] bf = be.doFinal(Base64.getDecoder().decode(bd));
            return new String(bf, StandardCharsets.UTF_8);
        } catch (Exception bg) {
            throw new RuntimeException("Error during decryption.", bg);
        }
    }
}