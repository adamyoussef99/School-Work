Adam Youssef
104785050

AYQ2: It looks like even though the fd was closed in the child process, the parent process
was still able to write to and close the fd after the child process did. So in the end, even
though the fd was closed in the child, it seems as though it remained open in the parent
process until it wrote to the file and closed it.