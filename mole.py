# mole.py by Jacob Steinebronn

# Operations for epsilon math
def eq(a, b): return abs(a-b) < 1e-4 
def neq(a,b): return not eq(a,b)
def lt(a, b): return neq(a,b) and a < b

# Operations for tuples
def add(a, b): return a[0]+b[0], a[1]+b[1]
def sub(a, b): return a[0]-b[0], a[1]-b[1]
def cross(a, b): return a[0]*b[1]-a[1]*b[0]
def cross2(a, b, c): return cross(sub(b, a), sub(c, a))
def dot(a, b): return a[0]*b[0]+a[1]*b[1]
def mult(s, a): return a[0]*s, a[1]*s
def div(s, a): return a[0]/s, a[1]/s

# Compute the point of intersection if lines are not parallel
# Returns -1 if they are
def line_intersection(a, b, c, d):
    t = cross(sub(b, a), sub(d, c))
    if(eq(t, 0)): return -1
    p = cross(sub(b, c), sub(d, c))
    q = cross(sub(d, c), sub(a, c))

    return div(t, add(mult(p, a), mult(q, b)))

# Returns the distance (squared) to the point of intersection of two moles
# Returns -1 if they do not intersect at the right moment (or at all)
# Return distance squared because we only care about relataive ordering 
# So we can save ourselves a few calls to sqrt() and some headaches with floats
def intersect(line1, line2):
    a, b = line1
    c, d = line2
    b = add(a, b)
    d = add(c, d)

    inter = line_intersection(a,b,c,d)
    if inter == -1:
        # We have parallel lines
        inter = div(2, add(a, c))
        if neq(cross2(a, inter, b), 0) or neq(cross2(c, inter, d), 0):
            return -1

    if lt(dot(sub(inter, a), sub(b, a)), 0) or lt(dot(sub(inter, c), sub(d, c)), 0):
        return -1

    if neq(dist2(a, inter), dist2(c, inter)):
        return -1

    return dist2(a, inter)

# return the square of the distance between points a and b
def dist2(a, b):
    dx = a[0]-b[0]
    dy = a[1]-b[1]
    return dx*dx+dy*dy

# get number of testcases
numTests = (int)(input().strip())

# For each testcase
for testNum in range(numTests):
    n = (int)(input().strip())
    moles = [-1]*n
    for i in range(n):
        x, y, dx, dy = list(map(int, input().strip().split()))
        moles[i] = ((x,y), (dx, dy))
    
    intersections = []

    # Get every mole pair's intersection, a tuple of (time, i, j)
    for i in range(n):
        for j in range(i+1, n, 1):
            intersection = intersect(moles[i], moles[j])
            if intersection == -1: continue
            intersections.append((intersection, i, j))
    
    intersections.sort(key=lambda tup:tup[0]) # sort on intersection time
    
    ans = [0]*n

    # rperm is the reverse permutation that is currently active
    rperm = [0]*n
    for i in range(n):
        rperm[i] = i
    
    # For each collision, increment whose it is, and swap those particles
    # in the permutation. So, particle i becomes j, and vice versa, because
    # they only swap momenta, so none of the collisions in the future change
    for time, i, j  in intersections:
        ans[rperm[i]] += 1
        ans[rperm[j]] += 1
        rperm[i], rperm[j] = rperm[j], rperm[i]
        
    for x in ans: print(x)


