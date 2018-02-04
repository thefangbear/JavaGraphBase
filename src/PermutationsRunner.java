/**
 * Created by derros on 2/3/18.
 */

public class PermutationsRunner {

    public static void main(String args[]) {
        Permutations<String> stringPermuter
                = new Permutations<>(new String[]{"Alice", "Bob", "Cecil", "David", "Edmund"});
        stringPermuter.permute(stringPermuter.getLen());
    }
}
