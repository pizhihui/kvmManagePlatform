/**
 * Created by pizhihui on 21/07/2017.
 */

function removeMachine(id, ip) {
    //var removeMachineBtn = document.getElementById(id);
    //removeMachineBtn.disabled = true;
    $.get(
        '/machine/checkVirtMachineState',
        {
            machineId: parseInt(id),
        },
        function(data){
            var machineState = data.data;
            var alertMsg;
            if (machineState == "running") {
                alertMsg = "该机器ip=" + ip + "还有运行中,确认要删除吗？";
            } else {
                alertMsg = "确认要删除ip=" + ip + "吗?";
            }
            if (confirm(alertMsg)) {
                location.href = "/machine/delete.do?machineId="+id;
            } else {
               //removeMachineBtn.disabled = false;
            }
        }
    );

}