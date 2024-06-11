import java.util.List;

public class kdTree {

      static class Node {
          double[] point;
          Node left,right;

          Node(double[] arr)
          {
              point = arr.clone();
              left = right = null;
          }
      }

      private Node root;
      private int K;

      public kdTree(int K){
          this.K=K;
          this.root=null;
      }

      public void insert(double[] point)
      {
          root=insertRec(root,point,0);
      }
      private Node insertRec(Node root,double[] point, int dimension){
          if(root==null)
          {
              return new Node(point);
          }
          int currentDimension = dimension % K;

          if(point[currentDimension] < root.point[currentDimension])
          {
              root.left = insertRec(root.left,point,dimension+1);
          }
          else {
              root.right = insertRec(root.right,point,dimension+1);
          }

          return root;
      }

      public double[] findNearest(double[] searchFor){
          return findNearestRec(root,searchFor,null,0, Double.MAX_VALUE);
      }

      private double[] findNearestRec(Node root,double[] searchFor ,double[] closest , int dimension , double closestDistance ){
          if(root ==null)
          {
              return closest;
          }


          //get current distance

          double dist = distance(searchFor,root.point);
          double[] closestNeighbor = closest;
          double closestSofar = closestDistance;



          if(dist < closestDistance){
              closestSofar = dist;
              closestNeighbor = root.point;
          }

          int currentDimension = dimension % K;
          Node preferredBranch = null;
          Node otherBranch = null;

          if(searchFor[currentDimension] < root.point[currentDimension] ){
              preferredBranch = root.left;
              otherBranch = root.right;
          }
          else {
              preferredBranch = root.left;
              otherBranch = root.right;
          }

          closest = findNearestRec(preferredBranch,searchFor,closestNeighbor,dimension+1,closestSofar);

          if(Math.abs(root.point[currentDimension]- searchFor[currentDimension])<closestSofar){
              closestNeighbor = findNearestRec(otherBranch,searchFor,closestNeighbor,dimension+1,closestSofar);
          }

          return closestNeighbor;


      }

    private double distance(double[] point1, double[] point2) {
        double sum = 0;
        for (int i = 0; i < K; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(sum);
    }

}
