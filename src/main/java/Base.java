import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Base {
    public static void main( String[] args ) throws IOException
    {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(is);
        String startStr=in.readLine();
        String endStr = in.readLine();



        int[] startAddr = parseIpAddress(startStr);
        int[] endAddr = parseIpAddress(endStr);
        showPossibleAddresses(startAddr,endAddr,startStr,endStr);
    }
    public static int[] parseIpAddress(String ip) throws IllegalArgumentException {
        StringTokenizer tok = new StringTokenizer(ip, ".");

        if (tok.countTokens() != 4) {
            throw new IllegalArgumentException("IP address must be in the format 'xxx.xxx.xxx.xxx'");
        }

        int[] data = new int[4];
        int i = 0;
        while (tok.hasMoreTokens()) {
            String strVal = tok.nextToken();
            try {
                int val = Integer.parseInt(strVal, 10);

                if (val < 0 || val > 255) {
                    throw new IllegalArgumentException("Illegal value '" + val + "' at byte " + (i + 1) + " in the IP address.");
                }

                data[i] = val;
                i++;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Illegal value '" + strVal + "' at token " + (i + 1) + " in the IP address.", e);
            }

        }
        return data;
    }
    public static void showPossibleAddresses(int[] start, int[] end, String inStr, String endStr) {
        //not sute that 255 exists there may be logical error
        for (int i = start[0]; i<=end[0]; i++){
            int end1 = (start[0]<end[0])?254:end[1];
            for (int j=start[1]; j<=end1; j++){
                int end2 = (start[1]<end[1])?254:end[2];
                for (int k=start[2]; k<=end2; k++) {
                    int end3 = (start[2]<end[2])?254:end[3];
                    for (int l=start[3]; l<=end3; l++) {
                        String outStr=i+"."+j+"."+k+"."+l;
                        if (!inStr.equals(outStr) && !endStr.equals(outStr))
                            System.out.println(i+"."+j+"."+k+"."+l);
                    }
                }
            }
        }
    }

}
