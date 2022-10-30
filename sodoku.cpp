#include <iostream>
#include <cmath> //for sqrt()
using namespace std;

const int SIZE = 9; //9
bool valid(const int s[][SIZE], int y, int x, int num);
void printSudoku(const int s[][SIZE]);
void solve(int s[][SIZE]);

int main()
{
            // Y    X
            //ROW COLUMN
  int sudoku[SIZE][SIZE] = {{3, 2, 1, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {4, 0, 0, 1}};

  /*
  int sudoku[SIZE][SIZE] = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                            {6, 0, 0, 1, 9, 5, 0, 0, 0},
                            {0, 9, 8, 0, 0, 0, 0, 6, 0},
                            {8, 0, 0, 0, 6, 0, 0, 0, 3},
                            {4, 0, 0, 8, 0, 3, 0, 0, 1},
                            {7, 0, 0, 0, 2, 0, 0, 0, 6},
                            {0, 6, 0, 0, 0, 0, 2, 8, 0},
                            {0, 0, 0, 4, 1, 9, 0, 0, 5},
                            //{0, 0, 0, 0, 8, 0, 0, 7, 9}}; //one solution
                            {0, 0, 0, 0, 8, 0, 0, 0, 0}}; //two solutions
  */
  cout << valid(sudoku, 1, 2, 3) << endl;
  //  cout << valid(sudoku, 4, 4, 5) << endl;
  solve(sudoku);

  return 0;
}


//This function will tell if num is possible at y(row), x(column)
bool valid(const int s[][SIZE], int y, int x, int num)
{
  //checks to see if num exists in this row (y)
  for(int i = 0; i < SIZE; i++)
    if(s[y][i] == num) //num exists
      return false;

  //checks to see if num exists in this column (x)
  for(int i = 0; i < SIZE; i++)
    if(s[i][x] == num) //num exists
      return false;

  //check to see if num exists within the SIZE x SIZE box
  int box_size = (int)sqrt(SIZE);
  int y0 = (y/box_size)*box_size;//y0 is the index of the top boundary of the box
  int x0 = (x/box_size)*box_size;//x0 is the index of the left boudary of the box
  for(int r = 0; r < box_size; r++)
    for(int c = 0; c < box_size; c++)
      if(s[y0+r][x0+c] == num)
        return false;

  //num wasn't found in the same row, colum or box
  return true;
}


//solve sudoku using a backtracking technique
void solve(int s[][SIZE])
{
  for(int i = 0; i < SIZE; i++)//for each row
    for(int j = 0; j < SIZE; j++)//go through each column
      {
        if(s[i][j] == 0)//If the current slot is 0, look for a number that works
          {
            for(int k = 1; k <= SIZE; k++) //a number can be 1 through SIZE
              {
                if(valid(s,i,j,k)) //call valid to see if the current number is possible
                  {
                    s[i][j] = k; //possible, then put the current number into the current slot.
                    solve(s); //call solve() recursively
                    s[i][j] = 0;//backtracked. it was a bad choice. Place 0 back in there. Try the next number.
                  }
              }

            //tried out num = 1 through SIZE, but none worked
            return; //there is no possible answer for the current slot. needs to backtrack (going back to the caller).
          }//end of if(possible)
      }//end of for(each column)

  printSudoku(s); //print each possible answer
  char ch;
  cin.get(ch); //read in <enter>
}



void printSudoku(const int s[][SIZE])
{
  cout << "----------------------" << endl;
  for(int r = 0; r < SIZE; r++)
    {
      for(int c = 0; c < SIZE; c++)
        cout << s[r][c] << " ";

      cout << endl;
    }
}
