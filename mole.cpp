// Solution for Mole (HSPT 2021 - On Site)
// Author: Atharva Nagarkar

#include <bits/stdc++.h>

using namespace std;

// handy functions for comparisons with an epsilon
const double EPSILON = 1e-6;
bool eq(double a, double b) { return abs(a - b) <= EPSILON; }
bool neq(double a, double b) { return abs(a - b) > EPSILON; }
bool gt(double a, double b) { return neq(a, b) && a > b; }
bool lt(double a, double b) { return neq(a, b) && a < b; }
bool geq(double a, double b) { return eq(a, b) || a >= b; }
bool leq(double a, double b) { return eq(a, b) || a <= b; }

// handy point struct with operator-overloaded functions for performing common functions
struct point {
    double x, y;
    point(double x, double y): x(x), y(y) {}
    double dist2() const { return x*x + y*y; }
    double dist() const { return sqrt(dist2()); }
    point operator+(point o) const { return point(x + o.x, y + o.y); }
    point operator-(point o) const { return point(x - o.x, y - o.y); }
    point operator*(double s) const { return point(x * s, y * s); }
    point operator/(double s) const { return point(x / s, y / s); }
    double cross(point o) const { return x * o.y - y * o.x; }
    double cross(point a, point b) const { return (a - *this).cross(b - *this); }
    double dot(point o) const { return x * o.x + y * o.y; }
    bool operator==(const point& o) const { return x == o.x && y == o.y; } 
};

// sentinal point to specify no intersection
const point INVALID_POINT(-1e18, -1e18);

// function to compute the intersections of lines P and Q
// where line P goes through points 'a' and 'b'
// and line Q goes through points 'c' and 'd'
point line_intersection(point& a, point& b, point& c, point& d) {
    double t = (b - a).cross(d - c);
    if(eq(t, 0)) { return INVALID_POINT; }
    double p = c.cross(b, d);
    double q = c.cross(d, a);
    return (a * p + b * q) / t;
}

// struct to hold the information about the molecule
struct molecule {
    point p, v;
    molecule(point p, point v): p(p), v(v) {}
    // function to calculate the intersection time of two molecules
    double intersect(molecule& o) {
        point a = p, b = p + v;
        point c = o.p, d = o.p + o.v;
        point inter = line_intersection(a, b, c, d);
        if(inter == INVALID_POINT) { // two paths are parallel
            inter = (a + c) / 2; // let the intersection be the midpoint of their initial positions
            // make sure this point is on the path of both molecules
            if(neq(a.cross(inter, b), 0) || neq(c.cross(inter, d), 0)) {
                return -1;
            }
        }
        // make sure that this point is in the direction of both molecules
        if(lt((inter - a).dot(b - a), 0) || lt((inter - c).dot(d - c), 0)) {
            return -1;
        }
        // make sure that both molecules reach this point at the same time
        if(neq((inter - a).dist(), (inter - c).dist())) {
            return -1;
        }
        // the time otherwise is the distance from one point to the intersection point
        return (a - inter).dist();
    }
};

// event struct to hold data about the intersection of two molecules
struct event {
    double t; // time of intersection
    int i, j; // indecies of the molecules who intersect
    event(double t, int i, int j): t(t), i(i), j(j) {}
    // comparator for sorting by increasing time of intersection
    bool operator<(const event& o) const {
        return t < o.t;
    }
};

int main() {
    
    // read in number of test cases
    int tests;
    cin >> tests;

    for(int tc = 1; tc <= tests; ++tc) {

        // read in number of molecules
        int n;
        cin >> n;

        // read and store the molecule information
        vector<molecule> molecules;
        for(int i = 0; i < n; ++i) {
            int x, y, dx, dy;
            cin >> x >> y >> dx >> dy;
            point p(x, y), v(dx, dy);
            molecules.emplace_back(p, v);
        }

        // maintain a list of intersection events of different molecules
        vector<event> eq;
        for(int i = 0; i < n; ++i) {
            for(int j = i + 1; j < n; ++j) {
                double intersect_time = molecules[i].intersect(molecules[j]);
                if(intersect_time != -1) {
                    eq.emplace_back(intersect_time, i, j);
                }
            }
        }

        // sort the events in increasing time fashion
        sort(eq.begin(), eq.end());

        // maintain the number of intersections for each molecule
        vector<int> answer(n);

        // keep a list of indecies for the molecules
        vector<int> indexes(n);
        iota(indexes.begin(), indexes.end(), 0); // fills the list from 0->(n-1)

        // go through each event
        // during the intersection, add to the indexes of both molecules
        // after the intersection, the two molecules follow each other's paths, so swap their indexes
        for(auto& e : eq) {
            answer[indexes[e.i]]++;
            answer[indexes[e.j]]++;
            swap(indexes[e.i], indexes[e.j]);
        }

        // print the final answer!
        for(int i = 0; i < n; ++i) {
            cout << answer[i] << '\n';
        }
    }

    return 0;
}
