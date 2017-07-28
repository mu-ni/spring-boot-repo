<template>
    <div id="dataTb">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Package Id</th>
                    <th>Image Key</th>
                    <th>Activation Date</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in content" @click="rowClicked(action, item.imgKey)">
                    <td>{{index+1}}</td>
                    <td>{{item.packageId}}</td>
                    <td>{{item.imgKey}}</td>
                    <td>{{item.activationDate}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
export default {
  name: 'dataTb',
  props: ['content', 'action'],
  methods: {
      rowClicked: function (action, id) {
          location.hash = action + '/' + id;
      }
  }
}
</script>
<style scoped>
#dataTb tr{
    cursor:pointer;
}
</style>
