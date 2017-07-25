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
function installVirtMachine(){

    var machineName = document.getElementById("machineName");
    if(machineName.value == ""){
        alert("虚拟名不能为空");
        machineName.focus();
        return false;
    }
    var machineIp = document.getElementById("machineIp");
    if(machineIp.value == ""){
        alert("虚拟机IP不能为空");
        machineIp.focus();
        return false;
    }
    if(!valIp.test(machineIp.value)) {
        alert("IP格式错误!");
        machineIp.focus();
    }
    var hostIp = document.getElementById("hostIp");
    if(hostIp.value == ""){
        alert("物理机IP不能为空");
        hostIp.focus();
        return false;
    }
    if(!valIp.test(machineIp.value)) {
        alert("IP格式错误!");
        machineIp.focus();
    }
    var machineCpu = document.getElementById("machineCpu");
    if(machineCpu.value == ""){
        alert("虚拟机CPU核数不能为空");
        machineCpu.focus();
        return false;
    }
    if(!valNum.test(machineCpu.value)) {
        alert("CPU核数必须为数字");
        machineCpu.focus();
    }
    var machineMem = document.getElementById("machineMem");
    if(machineMem.value == ""){
        alert("虚拟机内存不能为空");
        machineMem.focus();
        return false;
    }
    if(!valNum.test(machineMem.value)) {
        alert("CPU核数必须为数字");
        machineMem.focus();
    }
    var machineDesc = document.getElementById("machineDesc");
    if(machineDesc.value == ""){
        alert("虚拟机描述信息不能为空");
        machineDesc.focus();
        return false;
    }

    $.ajax({
        type: "post",
        data: {
            name: machineName.value,
            ip: machineIp.value,
            hostIp: hostIp.value,
            cpu: parseInt(machineCpu.value),
            mem: parseInt(machineMem.value),
            description: machineDesc.value
        },
        url: "/machine/installVirtMachineOp",
        beforeSend: function () {
            // 禁用按钮防止重复提交
            $("#installVirtMachineBtn").attr({ disabled: "disabled" });
            $("#installVirtMachineBtn").html("正在安装中....请稍等");
        },
        success: function (data) {
            if (data.code == 202) {
                //清空输入框
                alert("安装成功！");
            }else{
                alert("错误: " + data.msg);
                //updateAppDetailBtn.disabled = false;
                //$("#updateAppDetailInfo").html("<div class='alert alert-error' ><button class='close' data-dismiss='alert'>×</button><strong>Error!</strong>更新失败！</div>");
            }
        },
        complete: function () {
            $("#installVirtMachineBtn").removeAttr("disabled");
            $("#installVirtMachineBtn").html("开始安装");

        },
        error: function (data) {
            console.info("error: " + data.responseText);
        }
    });

    /*$.post(
        '/machine/installVirtMachineOp',
        {
            name: machineName.value,
            ip: machineIp.value,
            hostIp: hostIp.value,
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
    );*/

}