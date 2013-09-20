package sdrmath;

import sdrjava.MainScreen;;



public class CooleyTuckey
{

    public CooleyTuckey()
    {
    }

    public final float[] fftMag(float x[][])
    {
//        MainScreen.setArea((new StringBuilder()).append("\n length of X: ").append(x[0].length).toString());
        n = x[0].length;
        nu = (int)(Math.log(n) / Math.log(2D));
//        System.out.println("2D vale: "+2D+"     "+Math.log(2D)+"     "+nu);
        int n2 = n / 2;
        int nu1 = nu - 1;
        float xre[] = new float[n];
        float xim[] = new float[n];
//        float mag[] = new float[n2];
        float mag[] = new float[n];
        for(int i = 0; i < n; i++)
        {
            xre[i] = x[0][i];
            xim[i] = x[1][i];
        }

        int k = 0;
        for(int l = 1; l <= nu; l++)
        {
            for(; k < n; k += n2)
            {
//                for(int i = 1; i <= n2; i++)
            	for(int i = 1; i <= n2; i++)
                {
                    float p = bitrev(k >> nu1);
                    float arg = (6.283185F * p) / (float)n;
                    float c = (float)Math.cos(arg);
                    float s = (float)Math.sin(arg);
                    float tr = xre[k + n2] * c + xim[k + n2] * s;
                    float ti = xim[k + n2] * c - xre[k + n2] * s;
                    xre[k + n2] = xre[k] - tr;
                    xim[k + n2] = xim[k] - ti;
                    xre[k] += tr;
                    xim[k] += ti;
                    k++;
                }

            }

            k = 0;
            nu1--;
            n2 /= 2;
        }

        for(k = 0; k < n; k++)
        {
            int r = bitrev(k);
            if(r > k)
            {
                float tr = xre[k];
                float ti = xim[k];
                xre[k] = xre[r];
                xim[k] = xim[r];
                xre[r] = tr;
                xim[r] = ti;
            }
        }

        mag[0] = (float)Math.sqrt(xre[0] * xre[0] + xim[0] * xim[0]) / (float)n;
//        for(int i = 1; i < n / 2; i++)
        for(int i = 1; i < n; i++)
            mag[i] = (2.0F * (float)Math.sqrt(xre[i] * xre[i] + xim[i] * xim[i])) / (float)n;

        return mag;
    }

    private int bitrev(int j)
    {
        int j1 = j;
        int k = 0;
        for(int i = 1; i <= nu; i++)
        {
            int j2 = j1 / 2;
            k = (2 * k + j1) - 2 * j2;
            j1 = j2;
        }

        return k;
    }

    int n;
    int nu;
    int b;
}
