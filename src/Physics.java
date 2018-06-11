public abstract class Physics {

    static float a,b;
    static float x,y;

    public static int collision(){
        return 1;
    }

    public static int twoPointToLineFunction(float x1,float y1,float x2,float y2){
        if(x1<x2){
            a=(y2-y1)/(x2-x1);
        }if(x1>x2){
            a=(y1-y2)/(x1-x2);
        }if(x1==x2) return -1;

        b=y1-a*x1;

        return 1;
    }

   public static int crossFunction(float a1, float b1, float a2, float b2){
        if(a1-a2==0) return -1;
        x=(b2-b1)/(a1-a2);
        y=a1*x+b1;
        return 1;
    }

    public static int collisionPoint(float x1,float y1,float a1, float b1, float a2, float b2,float a3, float b3, float a4, float b4){

        boolean z1 = (y1<a1*x1+b1);
        boolean z2 = (y1<a2*x1+b2);
        boolean z3 = (y1>a3*x1+b3);
        boolean z4 = (y1>a4*x1+b4);

        if(z1&&z2&&z3&&z4) return 1;
        else
        return 0;
    }

    public static int perpendicularFunction(float x1,float y1,float a1, float b1){

        if(a1!=0) a=-1/a1;
        else return -1;
        b=y1-a*x1;
        return 1;
    }
}
