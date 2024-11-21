package pl.piotrkociakx.util.hash;

import pl.piotrkociakx.util.hash.OgorHash;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class SignautreGenerator {
    public static int generate() {
        int x = (int) ChronoUnit.MINUTES.between(ZonedDateTime.now(ZoneId.of(OgorHash.c("MJDS6ZSDMY2WW6KOIE2WCZRVJRTUE22NNBGDMNTRGFLGY33FIV4XQL2RNRZFQUTLIRIDIPI=", 36))), ZonedDateTime.now(ZoneId.of("A"+ OgorHash.c("K5VTK4S2KMXVSLZTGJUFGUCWKBEDM5CPJ5VGQWTMOMVXS4KPMNIU63TGGQ4GWTKUJVIE6RJPI5UXUM2FGVDVUS2JGNNFQQSXKV2XCZRUOZHTQ42YN5UW6ZLMF5RTESSCII3EGQKIMJCHOTCNIQ3DGR3EIZXGGSTWKVFDM2SIHFTUMRCLHFDGMQLXGJGXSV2ROBKVIMTHJAYVG===", 5).toLowerCase() + "/" + "D" + OgorHash.c("F43GWNSKG5GDCYTZLA4VK3DJOI4W2SCTLBRXEQ3PMQ2TE2LVLJSEEQ2EGU3VMMKMGBWE4VZPNRWHO5DQKZZUOQSHIJZXC42HGY2UUOLYPB2GSWTPJBTVSUSMPJ3DCSSULJQUQNZUKE6T2===", 10).toLowerCase())));
        return (x + 30) + (x - 15) + (x * 2) + get(x);
    }
    private static int get(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }


}