#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

#define MEM 3000

size_t compile(char*);	// Compiler
void execute(char*);	// Executer

int main(int argc, char *argv[]) {
	
    if (argc != 3) {
        printf("Usage: ./program <string>\n");
        return 1;
    }

	// Extract the passed string (BF source code)
    char *code = argv[2]; 

    printf("%s - %s",argv[1],code);
    return 0;

    // Reading 
    char mode = argv[1];
    if(mode == 'c'){
    	return compile(code);
	}
	if(mode == 'r'){
		execute(code);
	}

    return 0;
}

// Compiling the BF source code
size_t compile(char *code) {
	
	int i, loop=0;
	
	// Unexpected character - check
	for(i=0; i<strlen(code); i++){
		char c = code[i];
		if(c!='>' || c!='<' || c!='+' || c!='-' || c!='.' || c!=',' || c!='[' || c!=']'){
			return 1;
		}
	}
	
	// Checks on the loop
	for (i = 0; i<strlen(code); i++) {
        char c = code[i];
        if (c == '[') {
            loop++;
        } else if (c == ']') {
            loop--;
            if (loop < 0) {
                // Unmatched end of loop - check
                return 3;
            }
        }
    }

	// Not completed loop - check
    if (loop != 0) {
        return 2;
    }
}

// Executing the BF source code
void execute(char *code) {

	// Defining the program pointer
    char memory[MEM] = {0};
    char *ptr = memory;

    while (*code) {
        switch (*code) {
        	// Incrementing pointer
            case '>':
                ++ptr;
                break;
            // Decrementing pointer
            case '<':
                --ptr;
                break;
            // Incrementing the pointer selected value
            case '+':
                ++(*ptr);
                break;
            // Decrementing the pointer selected value
            case '-':
                --(*ptr);
                break;
            // ASCII output from the pointer selected value
            case '.':
                putchar(*ptr);
                break;
            // ASCII input to the pointer selected value
            case ',':
                *ptr = getchar();
                break;
            // LOOP START: exit the loop if the pointer selected value is zero
            case '[':
                if (*ptr == 0) {
                    int loop = 1;
                    while (loop > 0) {
                        ++code;
                        if (*code == '[')
                            ++loop;
                        else if (*code == ']')
                            --loop;
                    }
                }
                break;
            // LOOP END: repeat the loop if the pointer selected value is not zero    
            case ']':
                if (*ptr != 0) {
                    int loop = 1;
                    while (loop > 0) {
                        --code;
                        if (*code == ']')
                            ++loop;
                        else if (*code == '[')
                            --loop;
                    }
                }
                break;
        }
        ++code;
    }

}
