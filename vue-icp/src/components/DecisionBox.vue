<template>
    <div id="DecisionBox">
        <button id="okBtn" type="submit" class="btn btn-success" @click="submitClicked('pass')">通过</button>
        <button id="nokBtn" type="button" class="btn btn-danger" @click="nokClicked">未通过</button>
        <div id="reasonDiv">
            <ul class="list-unstyled">
                <li><input id="reason1" type="radio" name="reason" value="reason1" @click="radioClicked">&nbsp;reason1</input></li>
                <li><input id="reason2" type="radio" name="reason" value="reason2" @click="radioClicked">&nbsp;reason2</input></li>
                <li><input id="reason3" type="radio" name="reason" value="reason3" @click="radioClicked">&nbsp;reason3</input></li>
            </ul>
            <button id="submitBtn" type="submit" class="btn btn-default" disabled="true" @click="submitClicked('npass')">提交</button>
        </div>
    </div>
</template>
<script>
import Axios from 'axios'
export default {
    name: 'decisionBox',
    props: ['imgid', 'action'],
    methods: {
        submitClicked: function (result) {
            Axios.post('/' + this.action + '/' + this.imgid, {'result': result})
              .then(function (response) {
                  if (response.data['action'] && response.data['nextid']) {
                      location.hash = response.data['action'] + '/' + response.data['nextid'];
                  } else {
                      location.hash = '/main';
                  }
              })
              .catch(function (error) {
                  alert('Oppps! ' + error);
              });
        },
        nokClicked: function (event) {
            document.getElementById('okBtn').disabled = true;
            document.getElementById('reasonDiv').style.display = 'block';
        },
        radioClicked: function (event) {
            if (document.getElementById('reason1').checked || document.getElementById('reason2').checked || document.getElementById('reason3').checked) {
                 document.getElementById('submitBtn').disabled = false;
            } else {
                document.getElementById('submitBtn').disabled = true;
            }
        }
    }
}
</script>
<style scoped>
#reasonDiv {
    display: none;
}
</style>
