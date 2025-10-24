public class Main {

    static class Metrics {
        public int swaps = 0;
        public int loops = 0;
        void loop(){ loops++; }
        void swap(int[] a,int i,int j){
            int t=a[i]; a[i]=a[j]; a[j]=t; swaps++;
        }
    }

    static int[] copia(int[] v){
        int n=v.length;
        int[] c=new int[n];
        int i=0;
        while(i<n){ c[i]=v[i]; i++; }
        return c;
    }

    static Metrics comb(int[] a){
        Metrics m=new Metrics();
        int n=a.length;
        int gap=n;
        boolean trocou=true;
        while(gap>1 || trocou){
            m.loop();
            gap=(gap*10)/13; if(gap<1) gap=1;
            int i=0; trocou=false;
            while(i+gap<n){
                m.loop();
                if(a[i]>a[i+gap]){ m.swap(a,i,i+gap); trocou=true; }
                i++;
            }
        }
        return m;
    }

    static Metrics gnome(int[] a){
        Metrics m=new Metrics();
        int n=a.length;
        int i=0;
        while(i<n){
            m.loop();
            if(i==0 || a[i]>=a[i-1]){ i++; }
            else { m.swap(a,i,i-1); i--; }
        }
        return m;
    }

    static void insub(int[] a,int s,int e,Metrics m){
        int i=s+1;
        while(i<=e){
            m.loop();
            int k=a[i];
            int j=i-1;
            while(j>=s && a[j]>k){
                m.loop();
                a[j+1]=a[j];
                j--;
            }
            a[j+1]=k;
            i++;
        }
    }

    static Metrics bucket(int[] a){
        Metrics m=new Metrics();
        int n=a.length;
        if(n<=1) return m;

        int min=a[0], max=a[0];
        int i=0;
        while(i<n){
            m.loop();
            int v=a[i];
            if(v<min) min=v;
            if(v>max) max=v;
            i++;
        }
        int range=(max-min)+1;
        int B=n, cap=n;
        int[] aux=new int[B*cap];
        int[] sz=new int[B];

        i=0;
        while(i<n){
            m.loop();
            int v=a[i];
            int idx=(B*(v-min))/range; if(idx>=B) idx=B-1;
            int p=idx*cap+sz[idx];
            aux[p]=v; sz[idx]=sz[idx]+1;
            i++;
        }

        int k=0, b=0;
        while(b<B){
            m.loop();
            int s=sz[b];
            if(s>0){
                int t=0;
                while(t<s){
                    m.loop();
                    a[k+t]=aux[b*cap+t];
                    t++;
                }
                insub(a,k,k+s-1,m);
                k=k+s;
            }
            b++;
        }
        return m;
    }

    static Metrics bubbleflag(int[] a){
        Metrics m=new Metrics();
        int n=a.length;
        boolean trocou=true;
        int i=0;
        while(trocou && i<n-1){
            m.loop();
            trocou=false;
            int j=0;
            while(j<n-1-i){
                m.loop();
                if(a[j]>a[j+1]){ m.swap(a,j,j+1); trocou=true; }
                j++;
            }
            i++;
        }
        return m;
    }

    static Metrics selection(int[] a){
        Metrics m=new Metrics();
        int n=a.length;
        int i=0;
        while(i<n-1){
            m.loop();
            int min=i;
            int j=i+1;
            while(j<n){
                m.loop();
                if(a[j]<a[min]) min=j;
                j++;
            }
            if(min!=i) m.swap(a,i,min);
            i++;
        }
        return m;
    }

    static Metrics cocktail(int[] a){
        Metrics m=new Metrics();
        int n=a.length;
        int ini=0, fim=n-1;
        boolean trocou=true;
        while(trocou){
            m.loop();
            trocou=false;
            int i=ini;
            while(i<fim){
                m.loop();
                if(a[i]>a[i+1]){ m.swap(a,i,i+1); trocou=true; }
                i++;
            }
            if(!trocou) break;
            trocou=false;
            fim=fim-1;
            i=fim-1;
            while(i>=ini){
                m.loop();
                if(a[i]>a[i+1]){ m.swap(a,i,i+1); trocou=true; }
                i--;
            }
            ini=ini+1;
        }
        return m;
    }

    static void roda(String nome,int[] base){
        int[] a;
        System.out.println("==== "+nome+" ====");

        a=copia(base); Metrics m1=comb(a);
        System.out.println("Comb Sort -> swaps="+m1.swaps+" loops="+m1.loops);

        a=copia(base); Metrics m2=gnome(a);
        System.out.println("Gnome Sort -> swaps="+m2.swaps+" loops="+m2.loops);

        a=copia(base); Metrics m3=bucket(a);
        System.out.println("Bucket Sort -> swaps="+m3.swaps+" loops="+m3.loops);

        a=copia(base); Metrics m4=bubbleflag(a);
        System.out.println("Bubble (flag) -> swaps="+m4.swaps+" loops="+m4.loops);

        a=copia(base); Metrics m5=selection(a);
        System.out.println("Selection -> swaps="+m5.swaps+" loops="+m5.loops);

        a=copia(base); Metrics m6=cocktail(a);
        System.out.println("Cocktail -> swaps="+m6.swaps+" loops="+m6.loops);
        System.out.println();
    }

    public static void main(String[] args){
        int[] vetor1={12,18,9,25,17,31,22,27,16,13,19,23,20,30,14,11,15,24,26,28};
        int[] vetor2={5,7,9,10,12,14,15,17,19,21,22,23,24,25,27,28,29,30,31,32};
        int[] vetor3={99,85,73,60,50,40,35,30,25,20,15,14,13,12,11,10,9,8,7,6};
        roda("vetor1",vetor1);
        roda("vetor2",vetor2);
        roda("vetor3",vetor3);
    }
}
