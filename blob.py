#Scan in the number of test cases
t = int(input())
for runs in range(t):
    #Scan the number of children, and the number of collisions.
    n, m = map(int, input().split())
    #Create a list of list, that will store the childern each child is stuck to
    glued = [[] for i in range(n)]
    for i in range(m):
        #Scan in the children involved in this collision
        a, b = map(int,input().split())
        #If either child is already glued to 2 other children, this collision has no effect on them
        a-=1
        b-=1
        if(len(glued[a])== 2 or len(glued[b])==2):
            continue
        glued[a].append(b)
        glued[b].append(a)
    #Now we found all the connections, to find the size of the largest blob in the class we just need to find the size of the largest connected component
    maxBlob = 0
    #To do that we will initiate a bfs from each child to determine the size of that child's blob
    visited = [False for i in range(n)]
    for i in range(n):
        if visited[i]:
            #This child is part of a blob we've already examined
            continue
        visited[i] = True
        queue = []
        queue.append(i)
        #Stores the size of this blob
        count = 1
        while(len(queue)>0):
            currChild = queue.pop()
            #visit all the current children's immediate, glued connection
            for toChild in glued[currChild]:
                if(visited[toChild]):
                    continue
                visited[toChild] = True
                count+=1
                queue.append(toChild)
        #If the current blob has a bigger size than our maxBlob, the maxBlob should be the current blob
        maxBlob = max(maxBlob,count)
    print(maxBlob)