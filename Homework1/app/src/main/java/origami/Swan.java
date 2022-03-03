package origami;

// public - everything
// protected default + anything that extends Swan.
// default (no word) visible to everything in the same package
// private (self only)

import android.app.Notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Swan implements Comparable<Swan> {
    // If Swan is a "key" in a map, or a member of a set, then you need
    //   -- hashCode() for HashSet/HashMap, and a compatible equals()
    //        good hashCodes look random, but are the same for the same
    //        objects.
    //   -- compareTo() for TreeSet/TreeMap, and a compatible equals()
    //      OR hand over a Comparator with a compare(Swan a, Swan b) method
    //         to the corresponding Set/Map
    //         a < b && b < c ==> a < c
    //    -- DO NOT change a Swan if it is a key or set member.  The hash,
    //         and/or tree will not automatically know to readjust itsself.
    //
    //    -- Primitive Wrappers (+String) have all this stuff, and are
    //       immutable (const like c++ const), so you're good to go.

    //   For Linked/ArrayList you're ok.  The "key" is really always just
    //   the numbers 0...n-1.

    //
    //   Collections are Java templates, which does not generate type
    //   specific code (like C++ does), instead it generates a single
    //   version that works on references to Object only.  So really,
    //   there is only ArrayList < Object > () for example.  The compiler
    //   type-checks array.add(swan) to make sure swan is a kind of Swan,
    //   but the container does not know or care.
    //
    //       swans.add(swan) becomes
    //       swans.add((Object) (Swan) swan) to the compiler
    //       Swan third = swans.get(2) becomes
    //       Swan third = (Swan) swans.get(2);
    //
    //    So you cannot use Collections (or any template) for primitive
    //    types.  ---> WrapperTypes.  Use Integer(x) to hold int x;
    //
    //  Array types for specific primitives exist as well.
    //

    // static properties

    public static final String DEFAULT_MATERIAL = "paper";
    public static final double DEFAULT_WIDTH = 3.0;
    public static final double DEFAULT_HEIGHT = 5.0;
    public static final int STEPS = 7; // one int called STEPS ever.

    public static double areaOfRectangle(double height, double width) {
        return width * height;
    }

    public static final double DEFAULT_AREA = areaOfRectangle(DEFAULT_HEIGHT, DEFAULT_WIDTH);

    public String getMaterial() {
        return material;
    }

    public int getStep() {
        return step;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    private String material;
    private int step; // one int called "step" for every new Swan(...)
    private double width;
    private double height;

    //private List< Swan > cygnets = new ArrayList< Swan >(); // or LinkedList<Swan>()
    // Array=(&0,&1,&2,&3,...)
    //   - O(1) indexing.
    //   - O(n) inserts into middle
    //   - O(1) on average append  - O(n) max
    // Linked = (null,&0,&&1) (&&0,&1,&&2) (&&1,&2,&&3) ... (&&prev,&obj,&&next)
    //        = 0 <--> 1 <--> 2 <--> 3 ....
    //   - O(n) indexing
    //   - O(1) inserts / appends / prepend
    //

    // overload
    public boolean someMethod() { return true; }
    public boolean someMethod(int x) { return true; }
    // public boolean someMethod(int y) { return true; }

    @Override
    public boolean equals(Object o) {
        return compareTo((Swan) o) == 0;
    }

    @Override public Object clone() {
        return new Swan(this);
    }


    @Override
    public int compareTo(Swan swan) {
        int ans = Integer.compare(step,swan.step);
        if (ans != 0) return ans;
        ans = Double.compare(swan.width, width);
        if (ans != 0) return ans;
        ans = Double.compare(swan.height, height);
        if (ans != 0) return ans;
        ans = material.compareTo(swan.material);
        if (ans != 0) return ans;
        ans = compare(cygnets,swan.cygnets);
        return ans;
    }

    @Override
    public int hashCode() {
//        return System.identityHashCode(this);
        // highly problematic...
        return Objects.hash(material, step, width, height)
                ^ hashCode(cygnets);
    }

    static int hashCode(Set< Swan > a) {
        Iterator<Swan> i = a.iterator();
        int code = 0;
        while (i.hasNext()) {
            Swan swan = i.next();
            code = code ^ swan.hashCode();
        }
        return code;
    }

    static int compare(TreeSet< Swan > a, TreeSet< Swan > b) {
        Iterator<Swan> i = a.iterator();
        Iterator<Swan> j = b.iterator();

        while (i.hasNext() && j.hasNext()) {
            Swan aSwan = i.next();
            Swan bSwan = j.next();
            int cmp = aSwan.compareTo(bSwan);
            if (cmp != 0) return cmp;
        }
        if (i.hasNext() && !j.hasNext()) {
            return 1;
        }
        if (!i.hasNext() && j.hasNext()) {
            return -1;
        }
        return 0;
    }

    private TreeSet< Swan > cygnets = new TreeSet< Swan >(); // or TreeSet < Swan > ()
    // Hash = (&C0,&C1,&C2,...,&CN)
    //          C0=Set of Collisions on 0 (TreeSet)
    //          C1=set of Collisions on 1
    //   Hash Function H(object)->number - (seems random but isn't)

    //     Ex: H("alice") = 38, H("bob") = 237
    //      That means "alice" is in C38, and "bob" is in C237
    //
    // When working well:
    //   O(1) add/removes/lookups on average.
    // Need a good hash for this (hard) --- IDE builds one and use that!
    //
    //  Tree Balanced Binary Tree
    //
    //     item
    //     |left
    //        item
    //        |left
    //        |right
    //     |right
    //
    //          (&0,&&1,&&2)
    //      (&1,&&11,&&12)    (&2,&&21,&&22)
    //
    // balanced: longest chain ~ O(log n) long.
    //
    //   Insert/delete/find O(log n) -guaranteed efficiency
    //
    //  Need :  "<" some kind of ordering. (pretty easy).
    //
    //


    public void addCygnet(Swan cygnet) {
        cygnets.add(cygnet);
    }

    public Swan() {
        this(DEFAULT_MATERIAL, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Swan(String material, double width, double height) {
        String trimmedMaterial=material.trim();
        this.material = trimmedMaterial;
        this.step = 0; // redundant
        this.width = width;
        this.height = height;
    }

    public Swan(Swan copy) {
        this(copy.material,copy.width,copy.height);
        this.step = copy.step;
        this.cygnets = clone(copy.cygnets);
    }

    static TreeSet<Swan> clone(TreeSet<Swan> of) {
        return null;
    }

    // mutator
    public void advance() {
        if (step < STEPS) {
            ++step;
            for (Swan cygnet : cygnets) {
                cygnet.advance();
            }
        }
    }

    private Map< String , Swan > namedCygnets = new HashMap< String, Swan>();

    private Map< Swan , String > cygnetNames = new HashMap< Swan, String >();

    public void addNamedCygnet(String name, Swan cygnet) {
        cygnets.add(cygnet);
        namedCygnets.put(name, cygnet);
        cygnetNames.put(cygnet,name);
    }

    public void listAllCygnets() {
        for (Swan cygnet : cygnets) {
            if (cygnetNames.containsKey(cygnet)) {
                String name = cygnetNames.get(cygnet);
                System.out.println("" + cygnet + " named " + name);
            } else {
                System.out.println("" + cygnet);
            }
        }
    }


    public List<Integer> stats() {
        // int [] counts = new int [Swan.STEPS+1];


       ArrayList<Integer>  counts = new ArrayList<Integer>(Swan.STEPS+1);
       for (Swan cygnet : cygnets) {
           int cygnetStep = cygnet.getStep();
           while (counts.size() <= cygnetStep) {
                   counts.add(0);
           }
           counts.set(cygnetStep, counts.get(cygnetStep)+1);
       }

       return counts;
    }

    public String directions() {


        switch (step) {
            case 0:
                return "get the paper";
            case 1:
                return "fold it up.";
            case 2:
                return "fold it more.";
            default:
                return "look up on oragami.org for step " + step;
        }
    }

    public boolean finished() {
        return step >= STEPS;
    }


}
