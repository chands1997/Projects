//***************************************************************************
//			   HEADER FILES USED IN PROJECT
//***************************************************************************

#include<iostream.h>
#include<fstream.h>
#include<conio.h>
#include<process.h>
#include<graphics.h>
#include<stdio.h>
#include<ctype.h>
#include<iomanip.h>

//***************************************************************************
//			     INTRODUCTION FUNCTION
//***************************************************************************

void intro ()
{
	clrscr();
	textcolor(BLACK);textbackground(WHITE);
	gotoxy(29,9);
	cout<<"~~~~~~~~~~~~~~~~~~~~~~~~~~";
	gotoxy(30,10);
	cout<<"BANKING SYSTEM SOFTWARE";
	gotoxy(29,11);
	cout<<"~~~~~~~~~~~~~~~~~~~~~~~~~~";
	gotoxy(1,15);
	cout<<"MADE BY: Shiavngi Chand";
	gotoxy(1,17);
	cout<<"SCHOOL: Lotus Valley International School,Gurgaon";
	getch();
}
//***************************************************************************
//				CLASS FOR BANKING
//***************************************************************************

class bank
{
	private:
	int acno; //Account Number
	char name[51],acc_type;
	float balance;
	public:
	int retno()
	{
		return acno;
	}
	void showdata();
	void readdata();
	void debit(float);
	void credit(float);
	void bal(float,char);


} ;//CLASS ENDS HERE

//***************************************************************************
//			MEMBER FUNCTION DEFINATION
//***************************************************************************
void bank::readdata()
{

	cout<<"\nENTER THE DETAILS";
	cout<<"\n\nACCOUNT NUMBER: ";
	cin>>acno;
	cout<<"\nNAME OF ACCOUNT HOLDER: ";
	gets(name);
	cout<<"\nTYPE OF ACCOUNT: ";
	cout<<"\nCURRENT ACCOUNT OR SAVING ACCOUNT(ENTER c/s)";
	cin>>acc_type;
	cout<<"\nENTER AMOUNT: ";
	cin>>balance;
	bal(balance,acc_type);
	getch();

}
void bank::bal(float b,char t)
{
	if(t=='c'||t=='C')
	{
	    if(b>1000)
	    {
	    cout<<"\n\n\n\nACCOUNT CREATED!!!!!";
	    }
	    else
	    {
	    cout<<"\n\nAMOUNT TO LESS...PLEASE RE ENTER AMOUNT: ";
	    cin>>balance;
	    bal(balance,acc_type);
	    }
	}
	if(t=='s'||t=='S')
	{
	    if(b>500)
	    {
	    cout<<"\n\n\n\nACCOUNT CREATED!!!!!";
	    }
	    else
	    {
	    cout<<"\n\nAMOUNT TO LESS...PLEASE RE ENTER AMOUNT: ";
	    cin>>balance;
	    bal(balance,acc_type);
	    }
	}
	if(t!='c'&& t!='C'&& t!='s'&& t!='S')
	{
		cout<<"\nENTERED WRONG TYPE.PLEASE RE-ENTER DETAILS:";
		cout<<"\nTYPE OF ACCOUNT: ";
		cout<<"\nCURRENT ACCOUNT OR SAVING ACCOUNT(ENTER c/s)";
		cin>>acc_type;
		cout<<"\nENTER AMOUNT: ";
		cin>>balance;
		bal(balance,acc_type);
		getch();
	}

}
void bank::showdata()
{
   cout<<"\nACCOUNT NUMBER:"<<acno;
   cout<<"\nHODER'S NAME:";
   puts(name);
   cout<<"AMOUNT:"<<balance;
   cout<<"\nACCOUNT TYPE:";
   if(acc_type=='c'||acc_type=='C')
   {
   cout<<"CURRENT";
   }
   else
   {
   cout<<"SAVING";
   }
   getch();
}
void bank::debit(float amt)
{
	int bal;
	cout<<"\nCURRENT BALANCE:"<<balance;
	if(acc_type=='c'||acc_type=='C')
	{
		bal=balance-amt;
		if(bal<0)
		{
			cout<<"\nINSUFFICIENT BALANCE!!!";
		}
		else
		{
		balance=balance-amt;
		cout<<"\nNEW BALANCE:"<<balance;
		}
	}
	else if(acc_type=='s'||acc_type=='S')
	{
		bal=balance-amt;
		if(bal<0)
		{
			cout<<"\nINSUFFICIENT BALANCE!!!";
		}
		else
		{
			balance=balance-amt;
			cout<<"\nNEW BALANCE:"<<balance;
		}
	}
	getch();
}
void bank::credit(float amt)
{
    if (amt < 0)
    {
	cout<<"\nINCORRECT AMOUNT:"<< amt;
	return;
    }
	cout<<"\nCURRENT AMOUNT:"<<balance;
	balance=balance+amt;
	cout<<"\nNEW AMOUNT:"<<balance;
	getch();
}

//***************************************************************************
//			  FUNCTION TO CREATE ACCOUNT
//***************************************************************************
void create(fstream &f,bank ac )
{
      clrscr();
      int num,flag=0;
      textcolor(WHITE);textbackground(BLUE);
      cout<<"Enter Account Number:";
      cin>>num;
       f.open("BANK.txt",ios::in);
	while(f.read((char*)&ac,sizeof(bank)))
	{
		if(ac.retno()==num)
		{

			flag=1;
		}
	}
	f.close();
	if(flag==1)
	cout<<"Account already exists";
	else
	{
	    //f.seekp(ios::end);
	    f.open("BANK.txt",ios::out|ios::app);
	    ac.readdata();
	    f.write((char*)&ac,sizeof(bank));
	}
	f.close();
	getch();
};
//***************************************************************************
//			 FUNCTION TO DISPLAY ACCOUNT
//***************************************************************************
void display(fstream &f,bank &ac)
{
	textcolor(WHITE);textbackground(BLUE);
	clrscr();
	int num;
	cout<<"\nENTER ACCOUNT NUMBER: ";
	cin>>num;
	int flag=0;
	f.open("BANK.txt",ios::in);
	while(f.read((char*)&ac,sizeof(bank)))
	{
	    if(ac.retno()==num)
	    {
		ac.showdata();
		flag=1;
		getch();
	    }
	    else
	    flag=0;
	}
	f.close();
	if(flag==0)
	{
	cout<<"\nRECORD DOES NOT EXIST!!!";
	}

}

//***************************************************************************
//    			FUNCTION TO MODIFY ACCOUNT
//***************************************************************************


void modify(fstream &f,bank ac)
{
    textcolor(WHITE);textbackground(BLUE);
    int num,found=0;
    clrscr();

    cout<<"\n\nTO MODIFY ";
    cout<<"\n\nENTER ACCOUNT NUMBER";
    cin>>num;
    f.open("BANK.txt",ios::in|ios::out);
    while(f.read((char*)&ac,sizeof(bank)) && found==0)
	   {
		   if(ac.retno()==num)
		   {
		    ac.showdata();
		    cout<<"\nENTER NEW DETAILS "<<endl;
		    ac.readdata();
		    int pos=-1*sizeof(ac);
		    f.seekp(pos,ios::cur);
		    f.write((char*)&ac,sizeof(bank));
		    cout<<"\n\n RECORD UPDATED";
		    found=1;
		    }
	   }
    f.close();
    if(found==0)
    cout<<"\n\n RECORD NOT FOUND ";
    getch();
};


//***************************************************************************
//    			FUNTION TO DELETE ACCOUNT
//***************************************************************************


void del(fstream &f, bank &ac)
{
    textcolor(WHITE);textbackground(BLUE);
    int num;
    clrscr();

    cout<<"\n\n\nDELETE RECORD";
    cout<<"\n\nENTER ACCOUNT NUMBER: ";
    cin>>num;
    f.open("BANK.txt",ios::in|ios::out);
    fstream f2;
    f2.open("Temp.txt",ios::out);
    f.seekg(0);
    while(f.read((char*)&ac,sizeof(bank)))
    {
	 if(ac.retno()!=num)
	 {
		 f2.write((char*)&ac,sizeof(bank));
	 }
    }
    f2.close();
    f.close();
    remove("BANK.txt");
    rename("Temp.txt","BANK.txt");
    cout<<"\n\nRECORD DELETED";
    getch();
};
//***************************************************************************
//    		       FUNCTION TO DEPOSIT AND WITHDRAW
//***************************************************************************

void deposit_withdraw(int option,fstream &f, bank &ac)
{
	textcolor(WHITE);textbackground(BLUE);
	int num,found=0;
	float amt;
	clrscr();
	cout<<"\n\nENTER NUMBER: ";
	cin>>num;
	f.open("BANK.txt",ios::in|ios::out);
	while(f.read((char*)&ac,sizeof(bank)) && found==0)
	{
		if(ac.retno()==num)
		{
			ac.showdata();
			if(option==1)
			{
				cout<<"\n\nTO DEPOSITE AMOUNT ";
				cout<<"\n\nENTER AMOUNT: ";
				cin>>amt;
				ac.credit(amt);
			}
			if(option==2)
			{
				cout<<"\n\nTO WITHDRAW AMOUNT ";
				cout<<"\n\nENTER AMOUNT:";
				cin>>amt;
				ac.debit(amt);
			}
			int pos=-1*sizeof(ac);
			f.seekp(pos,ios::cur);
			f.write((char*)&ac,sizeof(bank));
			cout<<"\n\nRECORD UPDATED";
			found=1;
	       }
	 }
    f.close();
    if(found==0)
    cout<<"\n\n RECORD NOT FOUND ";
    getch();
} ;
//***************************************************************************
//			    FUNCTION TO DISPLAY ALL
//***************************************************************************

void display_all(fstream &f,bank &ac)
    {
    textcolor(BLACK);textbackground(WHITE);
     clrscr();
     f.open("BANK.txt",ios::in);
     if(!f)
     {
       cout<<"ERROR!!! FILE COULD NOT BE OPEN";
       getch();
       return;
     }

     while(f.read((char*)&ac,sizeof(bank)))
     {
	ac.showdata();
	cout<<"\n\n";
     }
     f.close();
};




//***************************************************************************
//				 MAIN FUNCTION
//***************************************************************************
void  main()
{
  fstream fp;
  bank b;
  char ch;
  intro ();
  do
    {
	  textcolor(WHITE);textbackground(BLUE);
	  clrscr();
	  cout<<"\n\n\n\tMAIN MENU";
	  cout<<"\n\n\t01. CREATE ACCOUNT";
	  cout<<"\n\n\t02. DEPOSIT MONEY";
	  cout<<"\n\n\t03. WITHDRAW MONEY";
	  cout<<"\n\n\t04. ENQUIRY ACCOUNT";
	  cout<<"\n\n\t05. ALL ACCOUNT HOLDER LIST";
	  cout<<"\n\n\t06. CLOSE AN ACCOUNT";
	  cout<<"\n\n\t07. MODIFY CUSTOMER ACCOUNT";
	  cout<<"\n\n\t08. EXIT";
	  cout<<"\n\n\tSelect Your Option (1-8) ";
	  ch=getche();
	  switch(ch)
	  {
		case '1': clrscr();
			  create(fp,b);
			  getch();
			  break;
		case '2': clrscr();
			  deposit_withdraw(1,fp,b);
			  getch();
			  break;
		case '3': clrscr();
			  deposit_withdraw(2,fp,b);
			  getch();
			  break;
		case '4': clrscr();
			  display(fp,b);
			  getch();
			  break;
		case '5': clrscr();
			  display_all(fp,b);
			  getch();
			  break;
		case '6': del(fp,b);
			  break;
		case '7': clrscr();
			  modify(fp,b);
			  getch();
			  break;
		case '8': exit(0);
		default : cout<<"\a";
	}
    }while(ch!='8');

}
