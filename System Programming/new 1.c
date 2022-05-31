#include <stdio.h>
#include <assert.h>
#include <string.h>
#include <stdlib.h>

// Linked list node storing a string of at most 4 characters
struct strnode {
  char str[5];
  struct strnode * next;
};

// create_node(str, next) creates a strnode containing the string str
//   and given next pointer; caller must free allocated memory using free_node
// requires: str has length at most 4
//           next is NULL or points to a strnode
// note: returns NULL if memory cannot be allocated
struct strnode * create_node(char str[], struct strnode * next){
    struct strnode * temp = malloc(sizeof(struct strnode)); // allocate memory, if this fails it'll return null
    if (temp == NULL) return NULL; // return null
    temp->next = next; // assign next to the next within temp
    strcpy(temp->str, str); // copy str over
    return temp; // return the updated value of temp
}

// free_node(node) frees the memory associated with the given node; returns a
//   pointer to the next node in the list previously headed by the given node
// requires: node is a valid strnode allocated dynamically
struct strnode * free_node(struct strnode * node){
    struct strnode * temp = node->next; // assign the pointer to the next strnode in the linked list to a temporary variable
    free(node); // free the memory associated with node
    return temp; // return the temporary variable containing node->next
}

// concat_nodes(head, str) modifies str to be a string representation of the
//   contents of the list with given head
// requires: str points to enough memory to store the output string
//           head is NULL or points to a strnode
char * concat_nodes(struct strnode * head, char * str) {
    int size;
    struct strnode * temp = create_node(head->str, head->next);
    str = (char*) malloc(strlen(temp->str)+1);
    strcpy(str, temp->str);

    while(temp != NULL) {
        if(temp == NULL) {
            break;
        }
        temp = temp->next;
        size = strlen(str)+strlen(temp->str)+1;
        str = (char *) realloc(str, size);
        strcat(str, temp->str);
    }
    return str;
}
    


int main() {
    struct strnode * last = create_node("last", NULL);
    struct strnode * middle = create_node("seco", last);
    struct strnode * first = create_node("firs", middle);

    assert(last->next == NULL);
    assert(middle->next == last);
    assert(first->next == middle);

    char * str = concat_nodes(first, str);
    printf("%s", str);

    assert(strcmp(last->str,"last") == 0);
    assert(strcmp(middle->str,"seco") == 0);
    assert(strcmp(first->str, "firs") == 0);

    assert(free_node(first) == middle);
    assert(free_node(middle) == last);
    assert(free_node(last) == NULL);

    puts("All tests passed!");

}