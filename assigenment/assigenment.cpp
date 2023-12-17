#include<bits/stdc++.h>
using namespace std;

void findLowAndHighest(const vector<vector<int>>&grid)
{
    //find each column's lowest and highest sum
    int i,j;
    int n = grid.size();
    int lSum=0;
    int hSum=0;
    int lowest[n]= {0};
    int highest[n]= {0};
    for(i=0; i<n; i++)
    {
        lSum=0;
        hSum=0;
        for(j=0; j<n; j++)
        {
            if(i==0)
            {
                lSum = lSum + min(grid[j][i], grid[j][i+1]);
                hSum = hSum + max(grid[j][i], grid[j][i+1]);
            }
            else if(i==n-1)
            {   if(i==n-1)
                lSum = lSum + min(grid[j][i], grid[j][i-1]);
                hSum = hSum + max(grid[j][i], grid[j][i-1]);
            }
            else
            {
                lSum = lSum + min(grid[j][i-1], min(grid[j][i], grid[j][i+1]));
                hSum = hSum + max(grid[j][i-1], max(grid[j][i], grid[j][i+1]));
            }

        }
        highest[i] = highest[i] + hSum;
        lowest[i] = lowest[i] + lSum;
    }

    //print each column's lowest and highest value
    cout<<"\n Lowest number array is: ";
    for(i=0; i<n; i++)
        cout<<lowest[i]<<" ";
    printf("\n Highest number array is: ");
    for(i=0; i<n; i++)
        cout<<highest[i]<<" ";

    //sort lowest and highest value and print
    sort(lowest, lowest+n);
    sort(highest, highest+n);
    printf("\n\n Lowest sum is: %d\n",lowest[0]);
    printf("\n Highest sum is: %d\n",highest[n-1]);
}

int main()
{
    int i,j,n;
    //since this is grid.so row & column same
    cout<<"Enter Desire Grid Dimension:";
    cin>>n;
    vector<vector<int>>grid(n, vector<int>(n));
    cout<<"Enter Grid element:\n";

    //take grid element
    for(i=0; i<n; i++)
        for(j=0; j<n; j++)
            cin>>grid[i][j];
    //passing grid as argument
    findLowAndHighest(grid);
}
/*
given input set for 6x6
6
1 5 1 5 1 5
3 3 2 3 3 4
2 3 4 4 3 2
2 2 3 2 2 4
2 2 4 3 4 2
4 4 4 4 2 3
another input set for 5x5
5
2 3 9 7 1
8 1 7 4 5
6 5 3 8 4
3 8 6 1 2
2 8 6 3 9
*/

