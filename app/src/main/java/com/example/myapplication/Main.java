class Main{
	int fibo(int n){
		if(n==0 or n==1){
			return 1;
		}
		return fibo(n-1)+fibo(n-2);
	}
	public static void main(String[] args){
		fibo(5);
	}
}

