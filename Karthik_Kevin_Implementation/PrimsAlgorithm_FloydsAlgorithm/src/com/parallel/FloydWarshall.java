package com.parallel;//####[1]####
//####[1]####
import java.util.ArrayList;//####[3]####
import java.util.Scanner;//####[4]####
import pt.runtime.TaskID;//####[6]####
//####[6]####
//-- ParaTask related imports//####[6]####
import pt.runtime.*;//####[6]####
import java.util.concurrent.ExecutionException;//####[6]####
import java.util.concurrent.locks.*;//####[6]####
import java.lang.reflect.*;//####[6]####
import pt.runtime.GuiThread;//####[6]####
import java.util.concurrent.BlockingQueue;//####[6]####
import java.util.ArrayList;//####[6]####
import java.util.List;//####[6]####
//####[6]####
class FloydWarshall {//####[8]####
    static{ParaTask.init();}//####[8]####
    /*  ParaTask helper method to access private/protected slots *///####[8]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[8]####
        if (m.getParameterTypes().length == 0)//####[8]####
            m.invoke(instance);//####[8]####
        else if ((m.getParameterTypes().length == 1))//####[8]####
            m.invoke(instance, arg);//####[8]####
        else //####[8]####
            m.invoke(instance, arg, interResult);//####[8]####
    }//####[8]####
//####[10]####
    static final int INF = 99999;//####[10]####
//####[12]####
    void floydWarshall(List<List<Integer>> graph, int vertices) {//####[13]####
        List<List<Integer>> dist = new ArrayList<List<Integer>>();//####[14]####
        for (int i = 0; i < vertices; i++) //####[16]####
        for (int j = 0; j < vertices; j++) //####[17]####
        dist.add(graph.get(i));//####[18]####
        TaskID id = getDistance(dist, vertices);//####[20]####
        TaskIDGroup sk = new TaskIDGroup(1);//####[21]####
        sk.add(id);//####[22]####
        try {//####[23]####
            sk.waitTillFinished();//####[24]####
        } catch (Exception e) {//####[25]####
            e.printStackTrace();//####[26]####
        }//####[27]####
        printSolution(dist, vertices);//####[30]####
    }//####[31]####
//####[35]####
    void printSolution(List<List<Integer>> dist, int vertices) {//####[36]####
        for (int i = 0; i < vertices; ++i) //####[37]####
        {//####[38]####
            for (int j = 0; j < vertices; ++j) //####[39]####
            {//####[40]####
                if (dist.get(i).get(j) == INF) //####[41]####
                System.out.print("INF "); else System.out.print(dist.get(i).get(j) + "   ");//####[42]####
            }//####[45]####
            System.out.println();//####[46]####
        }//####[47]####
    }//####[48]####
//####[50]####
    private static volatile Method __pt__getDistance_ListListInteger_int_method = null;//####[50]####
    private synchronized static void __pt__getDistance_ListListInteger_int_ensureMethodVarSet() {//####[50]####
        if (__pt__getDistance_ListListInteger_int_method == null) {//####[50]####
            try {//####[50]####
                __pt__getDistance_ListListInteger_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__getDistance", new Class[] {//####[50]####
                    List.class, int.class//####[50]####
                });//####[50]####
            } catch (Exception e) {//####[50]####
                e.printStackTrace();//####[50]####
            }//####[50]####
        }//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(List<List<Integer>> dist, int vertices) {//####[50]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[50]####
        return getDistance(dist, vertices, new TaskInfo());//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(List<List<Integer>> dist, int vertices, TaskInfo taskinfo) {//####[50]####
        // ensure Method variable is set//####[50]####
        if (__pt__getDistance_ListListInteger_int_method == null) {//####[50]####
            __pt__getDistance_ListListInteger_int_ensureMethodVarSet();//####[50]####
        }//####[50]####
        taskinfo.setParameters(dist, vertices);//####[50]####
        taskinfo.setMethod(__pt__getDistance_ListListInteger_int_method);//####[50]####
        taskinfo.setInstance(this);//####[50]####
        taskinfo.setInteractive(true);//####[50]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(TaskID<List<List<Integer>>> dist, int vertices) {//####[50]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[50]####
        return getDistance(dist, vertices, new TaskInfo());//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(TaskID<List<List<Integer>>> dist, int vertices, TaskInfo taskinfo) {//####[50]####
        // ensure Method variable is set//####[50]####
        if (__pt__getDistance_ListListInteger_int_method == null) {//####[50]####
            __pt__getDistance_ListListInteger_int_ensureMethodVarSet();//####[50]####
        }//####[50]####
        taskinfo.setTaskIdArgIndexes(0);//####[50]####
        taskinfo.addDependsOn(dist);//####[50]####
        taskinfo.setParameters(dist, vertices);//####[50]####
        taskinfo.setMethod(__pt__getDistance_ListListInteger_int_method);//####[50]####
        taskinfo.setInstance(this);//####[50]####
        taskinfo.setInteractive(true);//####[50]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(BlockingQueue<List<List<Integer>>> dist, int vertices) {//####[50]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[50]####
        return getDistance(dist, vertices, new TaskInfo());//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(BlockingQueue<List<List<Integer>>> dist, int vertices, TaskInfo taskinfo) {//####[50]####
        // ensure Method variable is set//####[50]####
        if (__pt__getDistance_ListListInteger_int_method == null) {//####[50]####
            __pt__getDistance_ListListInteger_int_ensureMethodVarSet();//####[50]####
        }//####[50]####
        taskinfo.setQueueArgIndexes(0);//####[50]####
        taskinfo.setIsPipeline(true);//####[50]####
        taskinfo.setParameters(dist, vertices);//####[50]####
        taskinfo.setMethod(__pt__getDistance_ListListInteger_int_method);//####[50]####
        taskinfo.setInstance(this);//####[50]####
        taskinfo.setInteractive(true);//####[50]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(List<List<Integer>> dist, TaskID<Integer> vertices) {//####[50]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[50]####
        return getDistance(dist, vertices, new TaskInfo());//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(List<List<Integer>> dist, TaskID<Integer> vertices, TaskInfo taskinfo) {//####[50]####
        // ensure Method variable is set//####[50]####
        if (__pt__getDistance_ListListInteger_int_method == null) {//####[50]####
            __pt__getDistance_ListListInteger_int_ensureMethodVarSet();//####[50]####
        }//####[50]####
        taskinfo.setTaskIdArgIndexes(1);//####[50]####
        taskinfo.addDependsOn(vertices);//####[50]####
        taskinfo.setParameters(dist, vertices);//####[50]####
        taskinfo.setMethod(__pt__getDistance_ListListInteger_int_method);//####[50]####
        taskinfo.setInstance(this);//####[50]####
        taskinfo.setInteractive(true);//####[50]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(TaskID<List<List<Integer>>> dist, TaskID<Integer> vertices) {//####[50]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[50]####
        return getDistance(dist, vertices, new TaskInfo());//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(TaskID<List<List<Integer>>> dist, TaskID<Integer> vertices, TaskInfo taskinfo) {//####[50]####
        // ensure Method variable is set//####[50]####
        if (__pt__getDistance_ListListInteger_int_method == null) {//####[50]####
            __pt__getDistance_ListListInteger_int_ensureMethodVarSet();//####[50]####
        }//####[50]####
        taskinfo.setTaskIdArgIndexes(0, 1);//####[50]####
        taskinfo.addDependsOn(dist);//####[50]####
        taskinfo.addDependsOn(vertices);//####[50]####
        taskinfo.setParameters(dist, vertices);//####[50]####
        taskinfo.setMethod(__pt__getDistance_ListListInteger_int_method);//####[50]####
        taskinfo.setInstance(this);//####[50]####
        taskinfo.setInteractive(true);//####[50]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(BlockingQueue<List<List<Integer>>> dist, TaskID<Integer> vertices) {//####[50]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[50]####
        return getDistance(dist, vertices, new TaskInfo());//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(BlockingQueue<List<List<Integer>>> dist, TaskID<Integer> vertices, TaskInfo taskinfo) {//####[50]####
        // ensure Method variable is set//####[50]####
        if (__pt__getDistance_ListListInteger_int_method == null) {//####[50]####
            __pt__getDistance_ListListInteger_int_ensureMethodVarSet();//####[50]####
        }//####[50]####
        taskinfo.setQueueArgIndexes(0);//####[50]####
        taskinfo.setIsPipeline(true);//####[50]####
        taskinfo.setTaskIdArgIndexes(1);//####[50]####
        taskinfo.addDependsOn(vertices);//####[50]####
        taskinfo.setParameters(dist, vertices);//####[50]####
        taskinfo.setMethod(__pt__getDistance_ListListInteger_int_method);//####[50]####
        taskinfo.setInstance(this);//####[50]####
        taskinfo.setInteractive(true);//####[50]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(List<List<Integer>> dist, BlockingQueue<Integer> vertices) {//####[50]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[50]####
        return getDistance(dist, vertices, new TaskInfo());//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(List<List<Integer>> dist, BlockingQueue<Integer> vertices, TaskInfo taskinfo) {//####[50]####
        // ensure Method variable is set//####[50]####
        if (__pt__getDistance_ListListInteger_int_method == null) {//####[50]####
            __pt__getDistance_ListListInteger_int_ensureMethodVarSet();//####[50]####
        }//####[50]####
        taskinfo.setQueueArgIndexes(1);//####[50]####
        taskinfo.setIsPipeline(true);//####[50]####
        taskinfo.setParameters(dist, vertices);//####[50]####
        taskinfo.setMethod(__pt__getDistance_ListListInteger_int_method);//####[50]####
        taskinfo.setInstance(this);//####[50]####
        taskinfo.setInteractive(true);//####[50]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(TaskID<List<List<Integer>>> dist, BlockingQueue<Integer> vertices) {//####[50]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[50]####
        return getDistance(dist, vertices, new TaskInfo());//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(TaskID<List<List<Integer>>> dist, BlockingQueue<Integer> vertices, TaskInfo taskinfo) {//####[50]####
        // ensure Method variable is set//####[50]####
        if (__pt__getDistance_ListListInteger_int_method == null) {//####[50]####
            __pt__getDistance_ListListInteger_int_ensureMethodVarSet();//####[50]####
        }//####[50]####
        taskinfo.setQueueArgIndexes(1);//####[50]####
        taskinfo.setIsPipeline(true);//####[50]####
        taskinfo.setTaskIdArgIndexes(0);//####[50]####
        taskinfo.addDependsOn(dist);//####[50]####
        taskinfo.setParameters(dist, vertices);//####[50]####
        taskinfo.setMethod(__pt__getDistance_ListListInteger_int_method);//####[50]####
        taskinfo.setInstance(this);//####[50]####
        taskinfo.setInteractive(true);//####[50]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(BlockingQueue<List<List<Integer>>> dist, BlockingQueue<Integer> vertices) {//####[50]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[50]####
        return getDistance(dist, vertices, new TaskInfo());//####[50]####
    }//####[50]####
    public TaskID<Void> getDistance(BlockingQueue<List<List<Integer>>> dist, BlockingQueue<Integer> vertices, TaskInfo taskinfo) {//####[50]####
        // ensure Method variable is set//####[50]####
        if (__pt__getDistance_ListListInteger_int_method == null) {//####[50]####
            __pt__getDistance_ListListInteger_int_ensureMethodVarSet();//####[50]####
        }//####[50]####
        taskinfo.setQueueArgIndexes(0, 1);//####[50]####
        taskinfo.setIsPipeline(true);//####[50]####
        taskinfo.setParameters(dist, vertices);//####[50]####
        taskinfo.setMethod(__pt__getDistance_ListListInteger_int_method);//####[50]####
        taskinfo.setInstance(this);//####[50]####
        taskinfo.setInteractive(true);//####[50]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[50]####
    }//####[50]####
    public void __pt__getDistance(List<List<Integer>> dist, int vertices) {//####[50]####
        int i, j, k;//####[51]####
        for (k = 0; k < vertices; k++) //####[52]####
        {//####[53]####
            for (i = 0; i < vertices; i++) //####[54]####
            {//####[55]####
                for (j = 0; j < vertices; j++) //####[56]####
                {//####[57]####
                    if (dist.get(i).get(k) + dist.get(k).get(j) < dist.get(i).get(j)) //####[58]####
                    dist.get(i).set(j, dist.get(i).get(k) + dist.get(k).get(j));//####[59]####
                }//####[60]####
            }//####[61]####
        }//####[62]####
    }//####[63]####
//####[63]####
//####[65]####
    public static void main(String[] args) {//####[66]####
        try {//####[72]####
            FloydWarshall a = new FloydWarshall();//####[73]####
            Scanner scanner = new Scanner(System.in);//####[74]####
            System.out.println("Please enter the number of vertices");//####[75]####
            int numVertices = scanner.nextInt();//####[76]####
            List<List<Integer>> graph = new ArrayList<List<Integer>>();//####[77]####
            Double d = 0.0;//####[78]####
            for (int i = 0; i < numVertices; i++) //####[79]####
            {//####[79]####
                List<Integer> elements1 = new ArrayList<Integer>();//####[80]####
                for (int j = 0; j < numVertices; j++) //####[81]####
                {//####[81]####
                    if (i == 0 || i == 3 || i == 6) //####[82]####
                    if (j == 0 || j == 3 || j == 6) //####[83]####
                    elements1.add(INF); else d = Math.floor(Math.random() * 10);//####[84]####
                    elements1.add(d.intValue());//####[87]####
                }//####[88]####
                graph.add(elements1);//####[89]####
                elements1 = null;//####[90]####
            }//####[92]####
            long startTime = System.nanoTime();//####[94]####
            a.floydWarshall(graph, numVertices);//####[95]####
            long endTime = System.nanoTime();//####[96]####
            long totalTime = endTime - startTime;//####[97]####
            double milliseconds = (double) totalTime / 1000000.0;//####[98]####
            System.out.println(milliseconds);//####[99]####
        } catch (Exception e) {//####[100]####
            e.printStackTrace();//####[101]####
        }//####[102]####
    }//####[103]####
}//####[103]####
