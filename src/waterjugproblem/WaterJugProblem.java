/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterjugproblem;
import java.util.*;


/**
 *
 * @author pankaj
 */
public class WaterJugProblem {

    static int gallon1,gallon2;
    static int wastage;
    static int steps;
    static int rules[]=new int[15];
    static int reset=0;
    static int ct=0;
    static int incr;
    static int tgallon1,tgallon2;
    static int check()
    {
    if(steps>10 || reset==1)
    return 1;
    else 
    if(gallon1==2)
    return 2;
    else
    return 0;
    }

    static void empty1()
    {
        wastage=wastage+gallon1;
        gallon1=0;
    } 
    static void empty2()
    {
        wastage=wastage+gallon2;
        gallon2=0;
    }
    static void fill1()
    {
        gallon1=gallon1+4;
    }
    static void fill2()
    {
        gallon2+=3;
    }
    static void gallon1_to_gallon2()
    {
        int temp=0;
        temp=3-gallon2;
        gallon2=3;
        gallon1=gallon1-temp;
    }
    static void gallon2_to_gallon1()
    {
    int temp=0;
    temp=4-gallon1;
    gallon1=4;
    gallon2=gallon2-temp;
    }
    static void empty1_fill2()
    {
    gallon2=gallon2+gallon1;
    gallon1=0;
    }
    static void empty2_fill1()
    {
    gallon1=gallon1+gallon2;
    gallon2=0;
    }


    static void call(int n)
    {

        tgallon1=gallon1;
        tgallon2=gallon2;

        try
        {
        //Thread.sleep(1000);
        }
        catch(Exception e)
        {
        System.out.println("Exception in thread");
        }
        if(steps>10)
        {
        System.out.println("ERROR num of steps greater than 10");
        }

        ++steps;
        if(n==1)
        fill1();
        //empty1();
        else
        if(n==2)
        fill2();
        //empty2();
        else
        if(n==3)
        empty1();
        //fill1();
        else
        if(n==4)
        empty2();
        //fill2();
        else
        if(n==5)
        gallon1_to_gallon2();
        else
        if(n==6)
        gallon2_to_gallon1();
        else
        if(n==7)
        empty1_fill2();
        else
        if(n==8)
        empty2_fill1();

        if(gallon1>4 || gallon1<0 || gallon2>3 || gallon2<0)
        reset=1;

    }

    static void repeat(int rules[])
    {
        gallon1=0;
        gallon2=0;
        wastage=0;
        steps=0;

        System.out.println("\nGallon1\tGallon2\tRule");
        System.out.println(gallon1+"\t"+gallon2+"\t");
        call(rules[0]);
        if(rules[0]!=0)
        System.out.println(gallon1+"\t"+gallon2+"\t"+rules[0]);
        call(rules[1]);
        if(rules[1]!=0)
        System.out.println(gallon1+"\t"+gallon2+"\t"+rules[1]);
        call(rules[2]);
        if(rules[2]!=0)
        System.out.println(gallon1+"\t"+gallon2+"\t"+rules[2]);
        call(rules[3]);
        if(rules[3]!=0)
        System.out.println(gallon1+"\t"+gallon2+"\t"+rules[3]);
        call(rules[4]);
        if(rules[4]!=0)
        System.out.println(gallon1+"\t"+gallon2+"\t"+rules[4]);
        call(rules[5]);
        if(rules[5]!=0)
        System.out.println(gallon1+"\t"+gallon2+"\t"+rules[5]);
        call(rules[6]);
        if(rules[6]!=0)
        System.out.println(gallon1+"\t"+gallon2+"\t"+rules[6]);
        call(rules[7]);
        if(rules[7]!=0)
        System.out.println(gallon1+"\t"+gallon2+"\t"+rules[7]);
        call(rules[8]);
        if(rules[8]!=0)
        System.out.println(gallon1+"\t"+gallon2+"\t"+rules[8]);
        call(rules[9]);
        if(rules[9]!=0)
        System.out.println(gallon1+"\t"+gallon2+"\t"+rules[9]);
    }
    public static void main(String args[])
    {
        Scanner scr=new Scanner(System.in);
        System.out.println("**********This is waterjug *************");
        System.out.println("\nRules:\n1.Fill Gallon 1.\n2.Fill Gallon 2.\n3.Empty gallon 1.\n4.Empty Gallon 2.\n5.Gallon 1 to Gallon 2 till full.\n6.Gallon 2 to Gallon 1 till full.\n7.Empty Gallon 1 and add to Gallon 2.\n8.Empty Gallon 2 and add to Gallon 1.");
        System.out.println("\nThe Optimal solution is the one with minimum steps and minimum wastage.\n");
        int flag=0;
        int count1=0;

        flag=0;
        count1=0;
        wastage=0;
        steps=0;
        for(int i=0;i<10;++i)
        rules[i]=0;
        reset=0;
        int temp1=0;
        incr=0;
        /************Data for testing the functionality****************/
        /*
        rules[0]=1;rules[1]=5;rules[2]=3;rules[3]=8;rules[4]=2;rules[5]=6;rules[6]=3;rules[7]=8;rules[8]=0;rules[9]=0;
        repeat(rules);
        System.out.println("\nWastage= "+wastage);
        rules[0]=2;rules[1]=8;rules[2]=2;rules[3]=6;rules[4]=3;rules[5]=8;rules[6]=0;rules[7]=0;rules[8]=0;rules[9]=0;
        repeat(rules);
        System.out.println("\nWastage= "+wastage);
        rules[0]=1;rules[1]=5;rules[2]=4;rules[3]=7;rules[4]=1;rules[5]=5;rules[6]=0;rules[7]=0;rules[8]=0;rules[9]=0;
        repeat(rules);
        System.out.println("\nWastage= "+wastage);
        */

        l1:for(int i=0;i<8;++i)
        {
        l2:for(int j=0;j<8;++j)
        {
        l3:for(int k=0;k<8;++k)
        {
        l4:for(int l=0;l<8;++l)
        {
        l5:for(int m=0;m<8;++m)
        {
        l6:for(int n=0;n<8;++n)
        {
        l7:for(int o=0;o<8;++o)
        {
        l8:for(int p=0;p<8;++p)
        {
        l9:for(int q=0;q<8;++q)
        {
        l10:for(int r=0;r<8;++r)
        {
        reset=0;
        call(i);
        call(j);
        call(k);
        call(l);
        call(m);
        call(n);
        call(o);
        call(p);
        call(q);
        call(r);
        if(gallon1==2 && reset==0)
        {
        try
        {
        Thread.sleep(10000);
        rules[0]=i;rules[1]=j;rules[2]=k;rules[3]=l;rules[4]=m;rules[5]=n;rules[6]=o;rules[7]=p;rules[8]=q;rules[9]=r;
        System.out.println("\nFound a pattern !!!!\n");
        repeat(rules);
        int count2=0;

        for(int z=0;z<10;++z)
        {
        if(rules[z]!=0)
        {
        //System.out.print(" "+rules[z]);
        ++count2;
        }
        }

        System.out.println("\nNumber of Steps= "+(count2+1));
        System.out.println("\nWastage= "+wastage);
        System.out.println("\n\n\nPlease wait for another solution.....");

        Thread.sleep(10000);
        }
        catch(Exception e)
        {}
        }

        gallon1=0;
        gallon2=0;
        steps=0;
        reset=0;
        wastage=0;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }

}
}