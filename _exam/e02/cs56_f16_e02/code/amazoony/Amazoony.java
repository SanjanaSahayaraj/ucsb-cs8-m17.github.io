public class Amazoony {
    public static void main (String [] args) {
        
        Song lf = new Song("Queen Latifah","Ladies First");
        Book rts = new Book("Julia Alvarez","Return To Sender",949,0.15);
        Product cstf = new Song("Justin Timberlake","Can't Stop The Feeling",79);
		// Product fl = new Product("Drake","Fake Love");
        //Shippable inf = new Shippable("Dan Brown","Inferno",999,1.5);
        Shippable tbt = new Book("Gwen Ifill","The Breakthrough",652,1.5);

        
        System.out.println("a:" + lf.getTitle());
        //System.out.println("b:" + rts.getArtist());
        System.out.println("c:" + cstf.getPrice());
        //System.out.println("d:" + fl.getPrice());
        //System.out.println("e:" + inf.getTitle());
        //System.out.println("f:" + tbt.getPrice());
        //System.out.println("g:" + lf.getWeight());
        System.out.println("h:" + rts.getTitle());
        System.out.println("i:" + cstf.getPrice());
        //System.out.println("j:" + fl.getPrice());
        
    } // main method
} // class Amazoony
