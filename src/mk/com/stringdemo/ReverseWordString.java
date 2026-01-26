package mk.com.stringdemo;

public class ReverseWordString {
	public static void main(String[] args) {
		String s1 = "mukesh kumar";
		//output = "hsekum ramuk"
		String[] s2 = s1.split(" ");
		String rev= "";
		
		for(int i=0; i<s2.length; i++) {
			for(int j=s2[i].length()-1 ; j>=0;j-- ) {
				rev = rev + s2[i].charAt(j);
			}
			rev = rev + " ";
		}
		System.out.println(rev);
	}
}
