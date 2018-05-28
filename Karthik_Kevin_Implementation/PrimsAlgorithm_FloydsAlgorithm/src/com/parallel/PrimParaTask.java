package com.parallel;//####[1]####
//####[1]####
import java.util.ArrayList;//####[2]####
import java.util.Arrays;//####[3]####
import java.util.Iterator;//####[4]####
import java.util.List;//####[5]####
import java.util.Scanner;//####[6]####
import pt.runtime.TaskID;//####[7]####
import pt.runtime.TaskIDGroup;//####[8]####
//####[8]####
//-- ParaTask related imports//####[8]####
import pt.runtime.*;//####[8]####
import java.util.concurrent.ExecutionException;//####[8]####
import java.util.concurrent.locks.*;//####[8]####
import java.lang.reflect.*;//####[8]####
import pt.runtime.GuiThread;//####[8]####
import java.util.concurrent.BlockingQueue;//####[8]####
import java.util.ArrayList;//####[8]####
import java.util.List;//####[8]####
//####[8]####
public class PrimParaTask {//####[10]####
    static{ParaTask.init();}//####[10]####
    /*  ParaTask helper method to access private/protected slots *///####[10]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[10]####
        if (m.getParameterTypes().length == 0)//####[10]####
            m.invoke(instance);//####[10]####
        else if ((m.getParameterTypes().length == 1))//####[10]####
            m.invoke(instance, arg);//####[10]####
        else //####[10]####
            m.invoke(instance, arg, interResult);//####[10]####
    }//####[10]####
//####[14]####
    public int minKey(List<Integer> keyList, List<Boolean> mstSetList) {//####[14]####
        int min = Integer.MAX_VALUE, min_index = -1;//####[17]####
        Iterator<Integer> iterator = keyList.iterator();//####[18]####
        int v = 0;//####[20]####
        while (iterator.hasNext()) //####[21]####
        {//####[21]####
            iterator.next();//####[22]####
            if (mstSetList.get(v) == Boolean.FALSE && keyList.get(v) < min) //####[23]####
            {//####[23]####
                min = keyList.get(v);//####[24]####
                min_index = v;//####[25]####
            }//####[26]####
            v++;//####[27]####
        }//####[28]####
        return min_index;//####[29]####
    }//####[30]####
//####[32]####
    void printMST(List parentList, int n, List<List> graphList) {//####[32]####
        System.out.println("Edge   Weight");//####[33]####
        for (int i = 1; i < n; i++) //####[34]####
        System.out.println(parentList.get(i) + " - " + i + "    " + graphList.get(i).get((int) parentList.get(i)));//####[35]####
    }//####[37]####
//####[39]####
    void primMST(List<List> graphList, int vertices) {//####[39]####
        Integer[] integers = new Integer[vertices];//####[40]####
        Integer[] maxIntegers = new Integer[vertices];//####[41]####
        Boolean[] booleans = new Boolean[vertices];//####[42]####
        Arrays.fill(integers, 0);//####[43]####
        Arrays.fill(maxIntegers, Integer.MAX_VALUE);//####[44]####
        Arrays.fill(booleans, Boolean.FALSE);//####[45]####
        List<Integer> parentList = Arrays.asList(integers);//####[46]####
        List<Integer> keyList = Arrays.asList(maxIntegers);//####[47]####
        List<Boolean> mstSetList = Arrays.asList(booleans);//####[50]####
        Iterator<Boolean> iterator = mstSetList.iterator();//####[51]####
        keyList.set(0, 0);//####[52]####
        parentList.set(0, -1);//####[53]####
        TaskID id = populateKeysQueue(mstSetList, graphList, keyList, parentList, vertices);//####[54]####
        TaskIDGroup sk = new TaskIDGroup(1);//####[55]####
        sk.add(id);//####[56]####
        try {//####[57]####
            sk.waitTillFinished();//####[58]####
        } catch (Exception e) {//####[59]####
            e.printStackTrace();//####[60]####
        }//####[61]####
        printMST(parentList, vertices, graphList);//####[63]####
    }//####[64]####
//####[66]####
    private static volatile Method __pt__populateKeysQueue_ListBoolean_ListList_ListInteger_ListInteger_int_method = null;//####[66]####
    private synchronized static void __pt__populateKeysQueue_ListBoolean_ListList_ListInteger_ListInteger_int_ensureMethodVarSet() {//####[66]####
        if (__pt__populateKeysQueue_ListBoolean_ListList_ListInteger_ListInteger_int_method == null) {//####[66]####
            try {//####[66]####
                __pt__populateKeysQueue_ListBoolean_ListList_ListInteger_ListInteger_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__populateKeysQueue", new Class[] {//####[66]####
                    List.class, List.class, List.class, List.class, int.class//####[66]####
                });//####[66]####
            } catch (Exception e) {//####[66]####
                e.printStackTrace();//####[66]####
            }//####[66]####
        }//####[66]####
    }//####[66]####
    public TaskID<Void> populateKeysQueue(Object mstSetList, Object graphList, Object keyList, Object parentList, Object vertices) {//####[66]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[66]####
        return populateKeysQueue(mstSetList, graphList, keyList, parentList, vertices, new TaskInfo());//####[66]####
    }//####[66]####
    public TaskID<Void> populateKeysQueue(Object mstSetList, Object graphList, Object keyList, Object parentList, Object vertices, TaskInfo taskinfo) {//####[66]####
        // ensure Method variable is set//####[66]####
        if (__pt__populateKeysQueue_ListBoolean_ListList_ListInteger_ListInteger_int_method == null) {//####[66]####
            __pt__populateKeysQueue_ListBoolean_ListList_ListInteger_ListInteger_int_ensureMethodVarSet();//####[66]####
        }//####[66]####
        List<Integer> __pt__taskIdIndexList = new ArrayList<Integer>();//####[66]####
        List<Integer> __pt__queueIndexList = new ArrayList<Integer>();//####[66]####
        if (mstSetList instanceof BlockingQueue) {//####[66]####
            __pt__queueIndexList.add(0);//####[66]####
        }//####[66]####
        if (mstSetList instanceof TaskID) {//####[66]####
            taskinfo.addDependsOn((TaskID)mstSetList);//####[66]####
            __pt__taskIdIndexList.add(0);//####[66]####
        }//####[66]####
        if (graphList instanceof BlockingQueue) {//####[66]####
            __pt__queueIndexList.add(1);//####[66]####
        }//####[66]####
        if (graphList instanceof TaskID) {//####[66]####
            taskinfo.addDependsOn((TaskID)graphList);//####[66]####
            __pt__taskIdIndexList.add(1);//####[66]####
        }//####[66]####
        if (keyList instanceof BlockingQueue) {//####[66]####
            __pt__queueIndexList.add(2);//####[66]####
        }//####[66]####
        if (keyList instanceof TaskID) {//####[66]####
            taskinfo.addDependsOn((TaskID)keyList);//####[66]####
            __pt__taskIdIndexList.add(2);//####[66]####
        }//####[66]####
        if (parentList instanceof BlockingQueue) {//####[66]####
            __pt__queueIndexList.add(3);//####[66]####
        }//####[66]####
        if (parentList instanceof TaskID) {//####[66]####
            taskinfo.addDependsOn((TaskID)parentList);//####[66]####
            __pt__taskIdIndexList.add(3);//####[66]####
        }//####[66]####
        if (vertices instanceof BlockingQueue) {//####[66]####
            __pt__queueIndexList.add(4);//####[66]####
        }//####[66]####
        if (vertices instanceof TaskID) {//####[66]####
            taskinfo.addDependsOn((TaskID)vertices);//####[66]####
            __pt__taskIdIndexList.add(4);//####[66]####
        }//####[66]####
        int[] __pt__queueIndexArray = new int[__pt__queueIndexList.size()];//####[66]####
        for (int __pt__i = 0; __pt__i < __pt__queueIndexArray.length; __pt__i++) {//####[66]####
            __pt__queueIndexArray[__pt__i] = __pt__queueIndexList.get(__pt__i);//####[66]####
        }//####[66]####
        taskinfo.setQueueArgIndexes(__pt__queueIndexArray);//####[66]####
        if (__pt__queueIndexArray.length > 0) {//####[66]####
            taskinfo.setIsPipeline(true);//####[66]####
        }//####[66]####
        int[] __pt__taskIdIndexArray = new int[__pt__taskIdIndexList.size()];//####[66]####
        for (int __pt__i = 0; __pt__i < __pt__taskIdIndexArray.length; __pt__i++) {//####[66]####
            __pt__taskIdIndexArray[__pt__i] = __pt__taskIdIndexList.get(__pt__i);//####[66]####
        }//####[66]####
        taskinfo.setTaskIdArgIndexes(__pt__taskIdIndexArray);//####[66]####
        taskinfo.setParameters(mstSetList, graphList, keyList, parentList, vertices);//####[66]####
        taskinfo.setMethod(__pt__populateKeysQueue_ListBoolean_ListList_ListInteger_ListInteger_int_method);//####[66]####
        taskinfo.setInstance(this);//####[66]####
        taskinfo.setInteractive(true);//####[66]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[66]####
    }//####[66]####
    public void __pt__populateKeysQueue(List<Boolean> mstSetList, List<List> graphList, List<Integer> keyList, List<Integer> parentList, int vertices) {//####[66]####
        int count = 0;//####[67]####
        try {//####[68]####
            while (count < vertices - 1) //####[69]####
            {//####[69]####
                for (Boolean boolean1 : mstSetList) //####[70]####
                {//####[70]####
                    int u = minKey(keyList, mstSetList);//####[71]####
                    mstSetList.set(u, Boolean.TRUE);//####[72]####
                    int v = 0;//####[73]####
                    for (Integer integer2 : keyList) //####[74]####
                    {//####[74]####
                        if ((int) graphList.get(u).get(v) != 0 && mstSetList.get(v) == Boolean.FALSE && (int) graphList.get(u).get(v) < keyList.get(v)) //####[75]####
                        {//####[76]####
                            parentList.set(v, u);//####[77]####
                            keyList.set(v, (int) graphList.get(u).get(v));//####[78]####
                        }//####[79]####
                        v++;//####[80]####
                    }//####[81]####
                    count++;//####[82]####
                }//####[83]####
            }//####[84]####
        } catch (Exception e) {//####[86]####
            e.printStackTrace();//####[87]####
        }//####[88]####
    }//####[89]####
//####[89]####
//####[91]####
    public static void main(String[] args) {//####[92]####
        int numVertices = 0;//####[93]####
        Scanner scanner = null;//####[94]####
        List<List> graphList = null;//####[95]####
        PrimParaTask t;//####[96]####
        try {//####[97]####
            t = new PrimParaTask();//####[98]####
            scanner = new Scanner(System.in);//####[99]####
            System.out.println("Please enter the number of vertices");//####[100]####
            numVertices = scanner.nextInt();//####[101]####
            graphList = new ArrayList<List>();//####[102]####
            for (int i = 0; i < numVertices; i++) //####[103]####
            {//####[103]####
                List<Integer> elements1 = new ArrayList<Integer>();//####[104]####
                for (int j = 0; j < numVertices; j++) //####[105]####
                {//####[105]####
                    Double d = Math.floor(Math.random() * 10);//####[106]####
                    elements1.add(d.intValue());//####[107]####
                }//####[108]####
                graphList.add(elements1);//####[109]####
                elements1 = null;//####[110]####
            }//####[112]####
            long startTime = System.nanoTime();//####[113]####
            t.primMST(graphList, numVertices);//####[114]####
            long endTime = System.nanoTime();//####[115]####
            long totalTime = endTime - startTime;//####[116]####
            double milliseconds = (double) totalTime / 1000000.0;//####[117]####
            System.out.println(milliseconds);//####[118]####
        } catch (Exception e) {//####[119]####
            e.printStackTrace();//####[120]####
            System.out.println("Please enter a number");//####[121]####
        }//####[123]####
    }//####[125]####
}//####[125]####
