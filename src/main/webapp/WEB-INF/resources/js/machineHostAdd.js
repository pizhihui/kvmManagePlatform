/**
 * Created by pizhihui on 19/07/2017.
 */

/**
 * 清除表单
 */
function resetForm() {
    //$("#addHostMachineForm").reset();
    $(':input','#addHostMachineForm')
        .not(':button, :submit, :reset, :hidden')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
}

// 验证ip
var valIp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
// 验证只能数字
var valNum=/^\d+$/

/**
 * 添加机器
 */
function addHostMachine(){

    var machineName = document.getElementById("machineName");
    if(machineName.value == ""){
        alert("物理机名不能为空");
        machineName.focus();
        return false;
    }
    var machineIp = document.getElementById("machineIp");
    if(machineIp.value == ""){
        alert("物理机IP不能为空");
        machineIp.focus();
        return false;
    }
    if(!valIp.test(machineIp.value)) {
        alert("IP格式错误!");
        machineIp.focus();
    }
    var machineCpu = document.getElementById("machineCpu");
    if(machineCpu.value == ""){
        alert("物理机CPU核数不能为空");
        machineCpu.focus();
        return false;
    }
    if(!valNum.test(machineCpu.value)) {
        alert("CPU核数必须为数字");
        machineCpu.focus();
    }
    var machineMem = document.getElementById("machineMem");
    if(machineMem.value == ""){
        alert("物理机内存不能为空");
        machineMem.focus();
        return false;
    }
    if(!valNum.test(machineMem.value)) {
        alert("CPU核数必须为数字");
        machineMem.focus();
    }
    var machineDesc = document.getElementById("machineDesc");
    if(machineDesc.value == ""){
        alert("物理机描述信息不能为空");
        machineDesc.focus();
        return false;
    }

    $.post(
        '/machine/addHostMachineOp',
        {
            name: machineName.value,
            ip: machineIp.value,
            hostIp: machineIp.value,
            cpu: parseInt(machineCpu.value),
            mem: parseInt(machineMem.value),
            description: machineDesc.value
        },
        function(data){
            console.log(data);
            if(data.code==202){
                alert("修改成功！");
                //$("#updateAppDetailInfo").html("<div class='alert alert-error' ><button class='close' data-dismiss='alert'>×</button><strong>Success!</strong>更新成功，窗口会自动关闭</div>");
                //setTimeout("$('updateAppDetailModal').modal('hide');reloadAppDetailPage("+appId+");",1000);
            }else{
                alert("错误: " + data.msg);
                //updateAppDetailBtn.disabled = false;
                //$("#updateAppDetailInfo").html("<div class='alert alert-error' ><button class='close' data-dismiss='alert'>×</button><strong>Error!</strong>更新失败！</div>");
            }
        }
    );

}