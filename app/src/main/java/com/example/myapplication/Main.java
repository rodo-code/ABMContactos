class Main{
	
	public bool primo(int numero){

	if(numero==1) return false;	
	for(int i=2;i<numero;i++){
		if(numero%i==0) return false;		
	}
	return true;
	}

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

